package com.jamie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注人物信息注解
 * @author jamie
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {

    /** 名字 */
    String name();

    /** 年龄 */
    int age() default 19;

    /** 性别 */
    String gender() default "男";

    /** 开发语言 */
    String[] language();

}
