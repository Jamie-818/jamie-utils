package com.jamie.config.pojo.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 用ConfigurationProperties注解获取配置
 * 
 * @author jamie
 * @date 2020/7/22 14:53
 * @ConfigurationProperties(prefix = "demo") // 配置前缀
 * @PropertySource(value = "config.properties") // 配置文件名
 */
@Component
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = "config.properties")
public class ConfigBeanProp {

    private String name;

    private String age;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAge() {

        return age;
    }

    public void setAge(String age) {

        this.age = age;
    }
}
