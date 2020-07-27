package com.jamie.apidoc.enums;

import lombok.Getter;

/**
 * 应用模块名称：返回
 * @author xuanweiyao
 * @since 2020/7/18 15:19 
 */
public enum RCodeEnums {
    /** 成功 */
    SUCCESS("00", "成功"),
    /** 参数异常 */
    PARAMS_ERROR("01", "请求参数异常"),
    /** 系统异常 */
    SYSTEM_ERROR("02", "未知系统异常"),
    /** 未登录 */
    NO_LOGIN("04", "未登录"),
    /** 用电户号未登录 */
    ELEC_NUMBER_NO_LOGIN("0401", "用电户号未登录"),
    /** 登录替换同一用户的旧的session时，旧的用户再次访问请求的响应代码 */
    DEL_OLD_SESSION("0408", "登录替换同一用户的旧的session时，旧的用户再次访问请求的响应代码"),
    /**  session中查不到数据，redis中数据超时失效 */
    SESSION_NO_DATA("0409", " session中查不到数据，redis中数据超时失效"),
    /** 没有注册 */
    NO_REGISTERED("05", "没有注册"),
    /** 未绑定 */
    NO_BINDS("0500", "未绑定"),
    /** 短信验证码错误 */
    VERIFY_CODE_ERROR("06", "短信验证码错误"),
    /** 图片验证码失败 */
    VERIFY_FAILED("0006", "图片验证码失败"),
    /** 次数限制 */
    TIMES_LIMIT("07", "次数限制"),
    /** 没有数据 */
    NO_DATA("09", "没有数据"),
    /** 强制登录 */
    NEED_LOGIN("10", "需要登录"),
    /** 调用MS失败 */
    MS_ERROR("404", "调用外部接口失败"),
    /** 无工单权限 */
    NO_AUTH_WORK("510", "无工单权限"),
    /** 重复数据 */
    SAME_COMMIT("98888889", "重复数据");

    @Getter
    private final String code;
    @Getter
    private final String desc;

    RCodeEnums(String code, String desc) {

        this.code = code;
        this.desc = desc;
    }
}
