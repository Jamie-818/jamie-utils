package com.jamie.pattern.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 验证各种懒汉模式是否可以被反射破解单例
 * @author jamie
 * @date 20.11.2 18:30
 */
public class SingletonTest {

    @DisplayName("验证通过反射去调用构造器是否能破坏懒汉模式")
    @Test
    public void enumStarvingSingleton() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        System.out.println(EnumStarvingSingleton.getInstance());
        Class<?> clazz = EnumStarvingSingleton.class;
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        EnumStarvingSingleton enumStarvingSingleton = (EnumStarvingSingleton)constructor.newInstance();
        System.out.println(enumStarvingSingleton.getInstance());
    }

    @DisplayName("验证通过反射去调用构造器是否能破坏懒汉模式单例")
    @Test
    public void lazyDoubleCheckSingleton() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        System.out.println(LazyDoubleCheckSingleton.getInstance());
        Class<?> clazz = LazyDoubleCheckSingleton.class;
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());
    }

    @DisplayName("验证通过反射去调用构造器是否能破坏饿汉模式单例")
    @Test
    public void starvingSingleton() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        System.out.println(StarvingSingleton.getInstance());
        Class<?> clazz = StarvingSingleton.class;
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());
    }

}
