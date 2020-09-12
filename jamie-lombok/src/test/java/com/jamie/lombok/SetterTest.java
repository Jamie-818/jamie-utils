package com.jamie.lombok;

import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.Setter;

/**
 * 演示：lombok @Setter注解
 * 为属性生成set方法
 * @author jamie
 * @date 2020/9/12 21:07
 */
public class SetterTest {

    /**
     * 普通生成set方法
     */
    @Setter
    private String field1;

    /**
     * 私有的set方法，且参数不能为空
     */
    @Setter(value = AccessLevel.PRIVATE, onParam_ = {@NotNull})
    private String field2;

}
