package com.hitsz.pae.util;/*
 *@Author:Simon
 *@Date: 2024-10-13 - 2024 10 13 16:23
 *@Description:web-project-actual-combat
 *@version:1.0
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*将配置文件中的属性值注入到实体类中，可以直接使用该实体类的属性*/
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    /*
    * 注意命名需要一一对应，将自动将aliyun.oss下的两个属性值注入*/
    private String endPoint;
    private String buketKey;
}
