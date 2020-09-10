package com.jamie.guava;

import org.junit.Test;

import java.util.Optional;

/**
 * 学习java8中的Optional使用方法
 * @author jamie
 * @date 2020/9/10 21:43
 */
public class OptionalTest {

    /**
     * 三种创建Optional对象方法
     */
    @Test
    public void test() throws RuntimeException {
        // 创建空的Optional对象
        Optional<Object> empty = Optional.empty();
        // 使用非null值创建Optional对象
        Optional<String> jamie = Optional.of("jamie");
        // 使用任意值创建Optional对象
        Optional<Object> optional = Optional.ofNullable("jamie");

        // 判断是否引用缺失的方法（建议不直接使用）
        boolean present = optional.isPresent();
        //  当optional引用存在时执行
        // 类似的方法： map filter flatMap
        optional.ifPresent(System.out::println);
        // 当optional引用缺失时执行,设置默认值
        Object orElse = optional.orElse("引用缺失");
        // 自定义引用缺失时自定义返回值
        Object orElseGet = optional.orElseGet(() -> {
            //自定义引用缺失
            return false;
        });
        // 自定义引用缺失时抛出异常
        //        optional.orElseThrow(() -> {
        //            throw new RuntimeException("引用缺失异常");
        //        });

    }

}
