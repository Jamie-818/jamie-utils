package com.jamie.validation;

import javax.validation.Valid;

/**
 * 用户信息服务类
 * @author jamie
 * @date 2020/9/13 12:15
 */
public class UserInfoService {

    /**
     * 作为输入参数
     * @param userInfo 用户信息
     */
    public void setUserInfo(@Valid UserInfo userInfo) {

    }

    /**
     * 作为输出参数
     * @return userInfo 用户信息
     */
    public UserInfo getUserInfo() {
        return new UserInfo();
    }

    /**
     * 默认构造函数
     */
    public UserInfoService() {
    }

    /**
     * 接收UserInfo作为参数的构造函数
     * @param info 用户信息
     */
    public UserInfoService(UserInfo info) {
    }

}
