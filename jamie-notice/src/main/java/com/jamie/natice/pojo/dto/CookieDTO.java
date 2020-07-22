package com.jamie.natice.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2020/7/10 15:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CookieDTO {
    private String jsessionId;
    private String serverId;
    private String route;

}
