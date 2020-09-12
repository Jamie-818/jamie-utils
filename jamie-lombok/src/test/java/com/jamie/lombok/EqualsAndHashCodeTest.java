package com.jamie.lombok;

import lombok.EqualsAndHashCode;

/**
 * 演示：lombok @EqualsAndHashCodeTest 注解
 * 生成Equals方法和HashCode方法
 * exclude = {"field1"} 排除某个字段
 * of = {"field1"} 指定字段
 * callSuper = true 调用分父类的方法
 * doNotUseGetters = true 不调用get方法进行获取值
 * onParam 给字段添加自定义注解
 * @author jamie
 * @date 2020/9/12 21:22
 */
@EqualsAndHashCode(exclude = {"field1"})
public class EqualsAndHashCodeTest {

    private String field1;

    private String field2;

}
