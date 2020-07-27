package com.jamie.notice.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2020/7/10 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpClientResponseDTO {
    private String response;
    private List<Cookie> cookies;

}
