package com.jamie.validation;

import org.junit.After;
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
    private UserInfo userInfo;

    // 验证结果集合
    private Set<ConstraintViolation<UserInfo>> set;

    /**
     * 初始化操作
     */
    @Before
    public void init() {
        // 初始化验证器
        validator = Validation.buildDefaultValidatorFactory()
                              .getValidator();
        // 初始化待验证对象 - 用户信息
        userInfo = new UserInfo();
    }

    /**
     * 结果打印
     */
    @After
    public void print() {
        set.forEach(item -> System.out.println(item.getMessage()));
    }

    /**
     * 验证 @NotNull 注解
     */
    @Test
    public void notNullValidation() {
        // 使用验证器对对象进行验证
        set = validator.validate(userInfo);
    }

}
