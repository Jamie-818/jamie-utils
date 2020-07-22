package com.jamie.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamie.config.pojo.bo.DemoPropertiesBO;
import com.jamie.config.pojo.prop.ConfigBeanProp;

/**
 * 获取配置测试类
 * 
 * @author jamie
 */
@RestController
public class PropController {
    @Value("${demo.name}")
    private String name;

    @Value("${demo.age}")
    private String age;

    @Autowired
    private DemoPropertiesBO demoPropertiesBO;

    @Autowired
    private Environment environment;
    @Autowired
    private ConfigBeanProp configBeanProp;

    /**
     * 1.使用@value注解注入获取配置
     * 
     * @author jamie
     * @date 2020/7/22 14:56
     */
    @GetMapping("/valueGetProp")
    public String valueGetProp() {
        return "get properties value by ''@Value'' :" + " name=" + name + " , age=" + age;
    }

    /**
     * 2.使用自定义对象获取配置,对象字段加@Value字段
     * 
     * @author jamie
     * @date 2020/7/22 14:57
     */
    @GetMapping(value = "/beanValueGetProp")
    public String beanValueGetProp() {
        return "get properties value by ''bean @Value'' :" + " name=" + demoPropertiesBO.name + " , age="
            + demoPropertiesBO.age;
    }

    /**
     * 3.使用Environment对象获取配置
     *
     * @author jamie
     * @date 2020/7/22 14:33
     */
    @GetMapping(value = "/environmentGetProp")
    public String environmentGetProp() {
        return "get properties value by ''Environment'' :" + " name=" + environment.getProperty("demo.name") + " , age="
            + environment.getProperty("demo.age");
    }

    /**
     * 4.使用ConfigurationProperties注解自定义类获取配置
     *
     * @author jamie
     * @date 2020/7/22 14:33
     */
    @GetMapping(value = "/configurationPropertiesGetProp")
    public String configurationPropertiesGetProp() {
        return "get properties value by ''ConfigurationProperties'' :" + " name=" + configBeanProp.getName() + " , age="
            + configBeanProp.getAge();
    }

}
