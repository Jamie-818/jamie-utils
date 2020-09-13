package com.jamie.validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 带验证对象实体类
 * 用户信息
 * @author jamie
 * @date 2020/9/13 11:21
 */
public class UserInfo {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private String userId;

    /**
     * 用户名
     * NotEmpty 不会自动去掉前后空格
     */
    @NotEmpty(message = "用户名称不能为空")
    private String userName;

    /**
     * 用户密码
     * NotBlank 自动去除字符串前后空格后验证是否为空
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度不能少于6位，大于20位")
    private String passWord;

    /**
     * 邮箱
     */
    @Email(message = "邮箱必须有效邮箱")
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 好友列表
     */
    private List<UserInfo> friends;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserInfo> friends) {
        this.friends = friends;
    }

}
