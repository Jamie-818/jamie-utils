package com.jamie.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 消息通知服务启动类
 * @author jamie
 * @date 2020/8/14 8:59
 */
@SpringBootApplication
@EnableScheduling
public class NoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

}
