package com.jamie.reflect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jamie
 *
 *         获取成员方法并调用：
 *
 *         1.批量的：
 *         public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
 *         public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
 *         2.获取单个的：
 *         public Method getMethod(String name,Class<?>... parameterTypes):
 *         参数：
 *         name : 方法名；
 *         Class ... : 形参的Class类型对象
 *         public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
 *
 *         调用方法：
 *         Method --> public Object invoke(Object obj,Object... args):
 *         参数说明：
 *         obj : 要调用方法的对象；
 *         args:调用方式时所传递的实参；
 *         ):
 */
public class MethodTest {

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
     * 获取所有的public方法，包括父类和Object
     */
    @Test
    public void getMethods() {
        //2、获取所有公有方法
        System.out.println("***************获取所有的public方法，包括父类和Object*******************");
        Method[] methodArray = reflectTargetClass.getMethods();
        for(Method m: methodArray){
            System.out.println(m);
        }
    }

    /**
     * ***************获取所有的方法，包括私有的*******************
     */
    @Test
    public void getDeclaredMethods() {
        //3、获取该类的所有方法
        System.out.println("***************获取所有的方法，包括私有的*******************");
        Method[] methodArray = reflectTargetClass.getDeclaredMethods();
        for(Method m: methodArray){
            System.out.println(m);
        }
    }

    /**
     * 获取公有的show1()方法
     */
    @Test
    public void getMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            InstantiationException {
        //4、获取单个公有方法
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = reflectTargetClass.getMethod("show1", String.class);
        System.out.println(m);
        //5、调用show1并执行
        ReflectTarget reflectTarget = (ReflectTarget)reflectTargetClass.getConstructor().newInstance();
        m.invoke(reflectTarget, "待反射方法一号");
    }

    /**
     * 获取私有的show4()方法
     */
    @Test
    public void getDeclaredMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            InstantiationException {
        //6、获取一个私有的成员方法
        System.out.println("***************获取私有的show4()方法******************");
        Method m = reflectTargetClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);
        ReflectTarget reflectTarget = (ReflectTarget)reflectTargetClass.getConstructor().newInstance();
        // 接收方法的返回值
        Object invoke = m.invoke(reflectTarget, 20);
        System.out.println("返回值 ： " + invoke);
    }

}

