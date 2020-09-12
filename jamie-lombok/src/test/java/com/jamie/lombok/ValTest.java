package com.jamie.lombok;

import lombok.val;

import java.util.ArrayList;

/**
 * 演示：lombok @val 注解
 * 弱语言变量
 * @author jamie
 * @date 2020/9/12 21:29
 */
public class ValTest {

    public ValTest() {
        val field = "jamie";
        val list = new ArrayList<>();
        list.add("jamie");
    }

}
