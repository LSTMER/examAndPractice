package com.hitsz.pae.interceptor;/*
 *@Author:Simon
 *@Date: 2024-11-10 - 2024 11 10 22:49
 *@Description:web-project-actual-combat
 *@version:1.0
 */

import com.aliyuncs.utils.StringUtils;
import com.hitsz.pae.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*令牌校验拦截器*/
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //判断是否需要拦截
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }
//        获取请求的url
        String url = request.getRequestURL().toString();

        if(url.contains("/login")||url.contains("/adminLogin")){
            /*如果是登录（包括学员登录，管理员登录）请求，将查看当前数据库中是否有已经登录的信息，若有，则删除登录信息，并将当前新的信息录入
            * 若无，则新增加一条token放入数据库中*/
            log.info("------------>当前是登录请求，直接放行");
            return true;
        }
        /*为了接下来的不需要继续，返回return*/
        String jwt = request.getHeader("Token");
        //判断令牌是否存在，若不存在，返回401
        if(StringUtils.isEmpty(jwt)){
            log.info("------------>令牌不存在，响应401");
            response.setStatus(401);
            return false;
        }

        /*若数据库中不存在该令牌，则提出账号已在别处登录*/

        Claims claims;
        try {
            claims = JwtUtils.parseJWT(jwt);
            String id = (String) claims.get("id");
            /*查询当前token是否被更改，即在别处被登录，若是，则返回错误，前端跳转到登录界面
            * 两者jwt第二段的过期时间会有所不同*/
            String JWT = (String) redisTemplate.opsForValue().get(id);
            assert JWT != null;
            if(!JWT.equals(jwt)){
                log.info("当前已在别处登录");
                response.setStatus(401);
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("当前令牌识别失败，返回401");
            response.setStatus(401);
            return false;
        }
        log.info("当前令牌识别成功，放行");
        return true;
    }
}
