package com.jamie.reflect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jamie
 *
 *         获取成员变量并调用：
 *
 *         1.批量的
 *         1).Field[] getFields():获取所有的"公有字段"
 *         2).Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有；
 *         2.获取单个的：
 *         1).public Field getField(String fieldName):获取某个"公有的"字段；
 *         2).public Field getDeclaredField(String fieldName):获取某个字段(可以是私有的)
 *
 *         设置字段的值：
 *         Field --> public void set(Object obj,Object value):
 *         参数说明：
 *         1.obj:要设置的字段所在的对象；
 *         2.value:要为字段设置的值；
 */
public class FieldTest {

    /** Class对象 */
    Class<?> reflectTargetClass = null;

    @Before
    public void init() throws ClassNotFoundException {
        // 获取class对象
        reflectTargetClass = Class.forName("com.jamie.reflect.ReflectTarget");
    }

    @After
    public void destroy() {
        System.out.println("************结束********************");
    }

    /**
     * 获取所有公有的字段
     */
    @Test
    public void getFields() {
        //1.获取所有公有的字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = reflectTargetClass.getFields();
        for(Field f: fieldArray){
            System.out.println(f);
        }
    }

    /**
     * 获取所有的字段(包括私有、受保护、默认的)
     */
    @Test
    public void getDeclaredFields() {
        //2.获取所有的字段
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] fieldArray = reflectTargetClass.getDeclaredFields();
        for(Field f: fieldArray){
            System.out.println(f);
        }
    }

    /**
     * 获取公有字段并调用
     */
    @Test
    public void getField() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {
        //3.获取单个特定公有的field
        System.out.println("*************获取公有字段并调用***********************************");
        Field f = reflectTargetClass.getField("name");
        System.out.println("公有的field name : " + f);
        ReflectTarget reflectTarget = (ReflectTarget)reflectTargetClass.getConstructor().newInstance();
        //4.给获取到的field赋值
        f.set(reflectTarget, "待反射一号");
        //5.验证对应的值name
        System.out.println("验证name : " + reflectTarget.name);
    }

    /**
     * 获取私有字段targetInfo并调用
     */
    @Test
    public void getDeclaredField() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {
        //3.获取单个特定公有的field
        System.out.println("**************获取私有字段targetInfo并调用********************************");
        ReflectTarget reflectTarget = (ReflectTarget)reflectTargetClass.getConstructor().newInstance();
        Field f = reflectTargetClass.getDeclaredField("targetInfo");
        System.out.println(f);
        // 调用私有的变量和构造函数，需要setAccessible为true
        f.setAccessible(true);
        f.set(reflectTarget, "13612341234");
        System.out.println("验证信息" + reflectTarget);
    }

}

