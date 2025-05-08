package com.hitsz.pae.util;/*
 *@Author:Simon
 *@Date: 2024-12-16 - 2024 12 16 19:14
 *@Description:xiaohashu
 *@version:1.0
 */


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.common.util.StringUtils;
import lombok.SneakyThrows;

public class JsonUtils {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static{
        /*这行代码告诉ObjectMapper在遇到JSON中存在但Java对象中没有对应属性的字段时不要抛出异常，而是忽略这些字段*/
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        /*当尝试序列化一个没有任何可访问属性的对象（即“空bean”）时，不抛出异常。通常情况下，如果你忘记
        给Jackson提供任何序列化信息，它会因为找不到要序列化的属性而失败。设置为false可以避免这种情况下的异常*/
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        /*
        *注册了JavaTimeModule模块，解决了Java 8中新的时间日期API（如LocalDateTime）的序列化问题。
        * 这是因为默认情况下，ObjectMapper不知道如何序列化或反序列化这些新类型的对象，所以需要显式地添加支持。
        * */
        OBJECT_MAPPER.registerModules(new JavaTimeModule());// 解决LocalDateTime的序列化问题
    }

    /**
     * 初始化：统一使用 Spring Boot 个性化配置的 ObjectMapper
     *
     * @param objectMapper
     */
    public static void init(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }
    /**
     * 将对象转化为json字符串
     * @param obj
     * @return
     */
    /*将受检查异常转换为不受检查异常*/
    @SneakyThrows
    public static String toJsonString(Object obj){
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    /**
     * 将 JSON 字符串转换为对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     * @param <T>
     */
    @SneakyThrows
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(jsonStr, clazz);
    }
}
