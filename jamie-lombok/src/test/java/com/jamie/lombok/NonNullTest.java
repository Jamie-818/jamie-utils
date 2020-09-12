package com.jamie.lombok;

import lombok.NonNull;

/**
 * 演示：lombok @NonNull 注解
 * 生成非空检查
 * @author jamie
 * @date 2020/9/12 21:31
 */
public class NonNullTest {

    public NonNullTest(@NonNull String field) {
        System.out.println(field);

    }

}
