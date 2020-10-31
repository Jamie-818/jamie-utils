package com.jamie.annotation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationParserTest {

    /** Class对象 */
    Class<?> clazz = null;

    @Before
    public void init() throws ClassNotFoundException {
        System.out.println("************开始********************");
        // 获取class对象
        clazz = Class.forName("com.jamie.annotation.Course");
    }

    @After
    public void destroy() {
        System.out.println("************结束********************");
    }

    /**
     * 解析类的注解
     */
    @Test
    public void parseTypeAnnotation() {
        //这里获取的是class对象的注解，而不是其里面的方法和成员变量的注解
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation: annotations){
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation)annotation;
            System.out.println("课程名：" + courseInfoAnnotation.courseName());
            System.out.println("课程标签：" + courseInfoAnnotation.courseTag());
            System.out.println("课程简介：" + courseInfoAnnotation.courseProfile());
            System.out.println("课程序号：" + courseInfoAnnotation.courseIndex());
        }
    }

    /**
     * 解析成员变量上的标签
     */
    @Test
    public void parseFieldAnnotation() {
        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields){
            //判断成员变量中是否有指定注解类型的注解
            boolean hasAnnotation = f.isAnnotationPresent(PersonInfoAnnotation.class);
            if(hasAnnotation){
                PersonInfoAnnotation personInfoAnnotation = f.getAnnotation(PersonInfoAnnotation.class);
                System.out.println("名字：" + personInfoAnnotation.name());
                System.out.println("年龄：" + personInfoAnnotation.age());
                System.out.println("性别：" + personInfoAnnotation.gender());
                for(String language: personInfoAnnotation.language()){
                    System.out.println("开发语言：" + language);
                }

            }
        }
    }

    /**
     * 解析方法注解
     */
    @Test
    public void parseMethodAnnotation() {
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method: methods){
            //判断方法中是否有指定注解类型的注解
            boolean hasAnnotation = method.isAnnotationPresent(CourseInfoAnnotation.class);
            if(hasAnnotation){
                CourseInfoAnnotation courseInfoAnnotation = method.getAnnotation(CourseInfoAnnotation.class);
                System.out.println("课程名：" + courseInfoAnnotation.courseName());
                System.out.println("课程标签：" + courseInfoAnnotation.courseTag());
                System.out.println("课程简介：" + courseInfoAnnotation.courseProfile());
                System.out.println("课程序号：" + courseInfoAnnotation.courseIndex());
            }
        }
    }

}
