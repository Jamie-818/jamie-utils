package com.jamie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注课程信息注解
 * @author jamrie
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseInfoAnnotation {

    /** 课程名称 */
    String courseName();

    /** 课程标签 */
    String courseTag();

    /** 课程简介 */
    String courseProfile();

    /** 课程序号 */
    int courseIndex() default 303;

}
