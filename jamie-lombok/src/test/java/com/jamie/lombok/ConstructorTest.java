package com.jamie.lombok;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 演示：lombok 构造函数注解 注解
 * @author jamie
 * @AllArgsConstructor 全参构造函数
 * @NoArgsConstructor 无参构造函数
 * @RequiredArgsConstructor 必要构造函数(字段定义不能为空)
 * @date 2020/9/12 21:35
 */
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class ConstructorTest {

    private final String field1;

    @NonNull
    private String field2;

    private String field3;

}
