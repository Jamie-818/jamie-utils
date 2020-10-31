package com.jamie.reflect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jamie
 *
 *         通过Class对象可以获取某个类中的：构造方法；
 *
 *         获取构造方法：
 *         1).批量的方法：
 *         public Constructor[] getConstructors()：所有"公有的"构造方法
 *         public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
 *         2).获取单个的方法，并调用：
 *         public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 *         public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 *
 *         调用构造方法：
 *         Constructor-->newInstance(Object... initargs)
 */
public class ConstructorTest {

    /** Class对象 */
    Class<?> clazz = null;

    @Before
    public void init() throws ClassNotFoundException {
        // 获取class对象
        clazz = Class.forName("com.jamie.reflect.ReflectTarget");
    }

    @After
    public void destroy() {
        System.out.println("************结束********************");
    }

    /**
     * 所有公有构造方法
     */
    @Test
    public void getMethods() {
        //1.获取所有的公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor<?>[] conArray = clazz.getConstructors();
        for(Constructor<?> c: conArray){
            System.out.println(c);
        }
    }

    /**
     * 所有的构造方法(包括：私有、受保护、默认、公有)
     */
    @Test
    public void getDeclaredConstructors() {
        //2.获取所有构造方法
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Constructor<?>[] conArray = clazz.getDeclaredConstructors();
        for(Constructor<?> c: conArray){
            System.out.println(c);
        }
    }

    /**
     * 获取公有、有两个参数的构造方法
     */
    @Test
    public void getConstructor() throws NoSuchMethodException {
        //3.获取单个带参数的公有方法
        System.out.println("*****************获取公有、有两个参数的构造方法*******************************");
        Constructor<?> con = clazz.getConstructor(String.class, int.class);
        System.out.println("con = " + con);
    }

    /**
     * 获取私有构造方法
     */
    @Test
    public void getDeclaredConstructor() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        //4.获取单个私有的构造方法
        System.out.println("******************获取私有构造方法*******************************");
        Constructor<?> con = clazz.getDeclaredConstructor(int.class);
        System.out.println("private con = " + con);
        System.out.println("******************调用私有构造方法创建实例*******************************");
        //暴力访问（忽略掉访问修饰符）
        con.setAccessible(true);
        ReflectTarget reflectTarget = (ReflectTarget)con.newInstance(1);
    }

}

