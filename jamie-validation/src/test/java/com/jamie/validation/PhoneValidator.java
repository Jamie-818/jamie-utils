package com.jamie.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 自定义手机号约束注解关联验证器
 * @author jamie
 * @date 2020/9/13 12:43
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String TEL_PHONE_NUMBER_VALID = "/^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)"
            + "\\d{8}$/";

    //  初始化方法
    public void initialize(Phone constraint) {
    }

    /**
     * 自定义校验逻辑
     * @param phone   手机号码
     * @param context Validator上下文
     * @return boolean
     */
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        // 手机号验证逻辑
        phone = Optional.ofNullable(phone)
                        .orElse("");
        return Pattern.matches(TEL_PHONE_NUMBER_VALID, phone);
    }

}
