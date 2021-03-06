package com.jamie.validation;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
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

    // 验证结果集合
    Set<ConstraintViolation<UserInfoService>> otherSet;

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
        userInfo2.setPassWord("11111");
        userInfo2.setEmail("buduideyouxiao@jami@jamie.com");
        userInfo2.setAge(17);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2099, Calendar.FEBRUARY, 1);
        userInfo2.setBirthday(calendar.getTime());
        userInfo2.setFriends(new ArrayList<>());
    }

    /**
     * 验证 validation 注解
     */
    @Test
    public void validation() {
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

    /**
     * 级联验证测试
     */
    @Test
    public void graphValidation() {
        ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>() {
            {
                add(new UserInfo());
            }

        };
        userInfo2.setFriends(userInfos);
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

    /**
     * 分组验证测试
     */
    @Test
    public void groupValidation() {
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2, UserInfo.LoginGroup.class);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

    /**
     * 分组排序GroupSequence验证测试
     */
    @Test
    public void groupSequenceValidation() {
        // 使用验证器对对象进行验证
        set1 = validator.validate(userInfo1);
        set1.forEach(item -> System.out.println("验证1的结果" + item.getMessage()));
        System.out.println("--------  分隔符  --------");
        set2 = validator.validate(userInfo2, UserInfo.Group.class);
        set2.forEach(item -> System.out.println("验证2的结果" + item.getMessage()));
    }

    /**
     * 对方法的输入参数进行校验
     */
    @Test
    public void paramValidation() throws NoSuchMethodException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        // 待验证对象
        UserInfoService service = new UserInfoService();
        // 待验证方法
        Method method = service.getClass()
                               .getMethod("setUserInfo", UserInfo.class);
        // 方法输入参数
        Object[] paramObject = {new UserInfo()};
        otherSet = executableValidator.validateParameters(service, method, paramObject);
        otherSet.forEach(item -> System.out.println(item.getMessage()));
    }

    /**
     * 对方法的返回值参数进行校验
     */
    @Test
    public void returnValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        // 构造要验证的方法对象
        UserInfoService service = new UserInfoService();

        Method method = service.getClass()
                               .getMethod("getUserInfo");
        // 调用方法得到返回值
        Object returnObject = method.invoke(service);
        // 校验方法返回值是否符合约束
        otherSet = executableValidator.validateReturnValue(service, method, returnObject);
        otherSet.forEach(item -> System.out.println(item.getMessage()));
    }

    /**
     * 对构造函数的输入参数进行校验
     */
    @Test
    public void constructorValidation() throws NoSuchMethodException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        // 获取构造函数
        Constructor<UserInfoService> constructor = UserInfoService.class.getConstructor(UserInfo.class);
        // 构造输入参数
        Object[] paramObject = {new UserInfo()};

        // 校验构造函数
        Set<ConstraintViolation<UserInfoService>> constraintViolations =
                executableValidator.validateConstructorParameters(constructor, paramObject);
        // 校验方法返回值是否符合约束
        constraintViolations.forEach(item -> System.out.println(item.getMessage()));
    }

}
