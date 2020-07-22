package com.jamie.natice.pojo.dto;

import java.util.List;

import org.apache.http.cookie.Cookie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
