package com.jamie.annotation;

/**
 * 验证注解是否会影响程序运行
 * @author jamie
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Course course = new Course();
        course.getCourseInfo();
        System.out.println("finish");
    }

}
