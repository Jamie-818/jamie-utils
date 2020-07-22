package com.jamie.config.pojo.bo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取demo配置对象
 * 
 * @author jamie
 */
// 注册成注解
@Component
public class DemoPropertiesBO {

    @Value("${demo.name}")
    public String name;

    @Value("${demo.age}")
    public String age;
}
