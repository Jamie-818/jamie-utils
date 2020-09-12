package com.jamie.lombok;

import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * 演示：lombok @Getter注解
 * 为属性生成get方法
 * @author jamie
 * @date 2020/9/12 20:56
 */

public class GetterTest {

    /**
     * 普通生成get方法
     */
    @Getter
    private String field1;

    /**
     * 私有的get方法，且不能为空
     */
    @Getter(value = AccessLevel.PRIVATE, onMethod_ = {@NotNull})
    private String field2;

    /** 静态常量懒加载的get方法 */
    @Getter(lazy = true)
    private final String field3 = "jamie";

}
