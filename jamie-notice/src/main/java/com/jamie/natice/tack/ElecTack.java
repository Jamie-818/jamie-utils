package com.jamie.natice.tack;

import com.alibaba.fastjson.JSON;
import com.jamie.natice.pojo.dto.CookieDTO;
import com.jamie.natice.pojo.dto.ElecDTO;
import com.jamie.natice.pojo.prop.JamieMailProp;
import com.jamie.natice.utils.ElecUtils;
import com.jamie.natice.utils.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

/**
 * 应用模块名称：定时器
 *
 * @author show
 * @since 2020/7/10 11:07
 */
@Configuration
@EnableScheduling
@Slf4j
public class ElecTack {
    @Autowired
    private JamieMailProp jamieMailProp;
    @Autowired
    private JavaMailSender javaMailSender;

    // @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 0 9,18 * * ?")
    private void elecTack() throws MessagingException {
        log.info("执行静态定时任务时间: " + LocalDateTime.now());
        this.testSend();
    }

    public void testSend() throws MessagingException {
        String loginPhone = jamieMailProp.getLoginPhone();
        String password = jamieMailProp.getPassword();
        String emailFrom = jamieMailProp.getEmailFrom();
        List<String> emailTo = jamieMailProp.getEmailTo();
        CookieDTO login = ElecUtils.login(loginPhone, password);
        String elecHtml = ElecUtils.getElecHtml(login, loginPhone, password);
        ElecDTO elecNumber = ElecUtils.getElecNumber(elecHtml);
        String todayElec = elecNumber.getTodayElec();
        String surplusElec = elecNumber.getSurplusElec();
        String monthElec = elecNumber.getMonthElec();
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info(
            "时间：" + localDateTime.toString() + "，今日用电：" + todayElec + ",剩余电量：" + surplusElec + ",本月用电：" + monthElec);
        double surplusElecNumber = Double.parseDouble(surplusElec.split("度")[0]);
        // 剩余电量低于20度就发邮件
        if (surplusElecNumber <= 20.0) {
            for (String emailAddress : emailTo) {
                log.info("正在发送邮件给" + emailAddress);
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                String title = "用电通知-今日用电:" + todayElec + ",剩余电量：" + surplusElec + ",本月累计用电：" + monthElec;
                messageHelper.setSubject(title);
                messageHelper.setFrom(emailFrom);
                messageHelper.setTo(emailAddress);
                messageHelper.setText(elecHtml, true);
                javaMailSender.send(messageHelper.getMimeMessage());
                log.info("邮件标题《" + title + "》发送成功");
            }
        }
    }

    public static void main(String[] args) {
        Properties p = PropertiesUtils.getProperties("application.properties");
        String loginPhone = p.getProperty("jamie.mail.loginPhone");
        String password = p.getProperty("jamie.mail.password");
        CookieDTO login = ElecUtils.login(loginPhone, password);
        String elecHtml = ElecUtils.getElecHtml(login, loginPhone, password);
        System.out.println(elecHtml);
        ElecDTO elecNumber = ElecUtils.getElecNumber(elecHtml);
        String todayElec = elecNumber.getTodayElec();
        String surplusElec = elecNumber.getSurplusElec();
        String monthElec = elecNumber.getMonthElec();
        System.out.println(JSON.toJSONString(elecNumber));
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info(
            "时间：" + localDateTime.toString() + "，今日用电：" + todayElec + ",剩余电量：" + surplusElec + ",本月用电：" + monthElec);
    }

}
