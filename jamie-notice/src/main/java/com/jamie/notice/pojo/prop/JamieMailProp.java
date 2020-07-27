package com.jamie.notice.pojo.prop;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 获取jamie的email配置
 * 
 * @author jamie
 */
@Component
@ConfigurationProperties(prefix = "jamie.mail")
@PropertySource(value = "application.properties")
@Getter
@Setter
public class JamieMailProp {
    /** 登录手机号 */
    private String loginPhone;
    /** 登录密码 */
    private String password;
    /** 发送者的邮箱，必须和邮件配置的一致 */
    private String emailFrom;
    /** 接收者的邮箱 */
    private List<String> emailTo;

}
