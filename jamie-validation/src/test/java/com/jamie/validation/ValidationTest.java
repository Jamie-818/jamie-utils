package com.jamie.validation;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 验证测试类
 * @author jamie
 * @date 2020/9/13 11:18
 */
public class ValidationTest {

    // 验证器对象
    private Validator validator;

    // 待验证对象
    private UserInfo userInfo1;

    private UserInfo userInfo2;

    // 验证结果集合
    private Set<ConstraintViolation<UserInfo>> set1;

    // 验证结果集合
    private Set<ConstraintViolation<UserInfo>> set2;

    /**
     * 初始化操作
     */
    @Before
    public void init() {
        // 初始化验证器
        validator = Validation.buildDefaultValidatorFactory()
                              .getValidator();
        // 初始化待验证对象 - 用户信息
        userInfo1 = new UserInfo();
        userInfo2 = new UserInfo();
        userInfo2.setUserId("雪花ID");
        userInfo2.setUserName(" ");
        userInfo2.setPassWord(" ");
    }

    /**
     * 验证 @NotNull 注解
     */
    @Test
    public void notNullValidation() {
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

    /**
     * 验证 @NotEmpty 注解
     */
    @Test
    public void notEmptyValidation() {
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

    /**
     * 验证 @NotBlank 注解
     */
    @Test
    public void notBlankValidation() {
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

}
