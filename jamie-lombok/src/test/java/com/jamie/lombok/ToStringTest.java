package com.jamie.lombok;

import lombok.Setter;
import lombok.ToString;

/**
 * 演示：lombok ToString 注解
 * 生成toString方法
 * includeFieldNames = false 不包含字段名
 * exclude = {"field1"} 排查字段
 * of = {"field1"} 指定字段
 * doNotUseGetters = true 不调用get方法进行获取值
 * @author jamie
 * @date 2020/9/12 21:08
 */
@ToString(includeFieldNames = false, doNotUseGetters = false)
public class ToStringTest {

    @Setter
    private String field1;

    @Setter
    private String field2;

    /**
     * 测试 doNotUseGetters = true 时，有没有调用get
     */
    public String getField2() {
        System.out.println("调用get方法");
        return this.field2;
    }

    public static void main(String[] args) {
        ToStringTest toStringTest = new ToStringTest();
        toStringTest.setField1("1");
        toStringTest.setField2("2");
        System.out.println(toStringTest.toString());
    }

}
