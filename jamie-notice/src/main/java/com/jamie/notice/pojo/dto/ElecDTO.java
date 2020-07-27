package com.jamie.notice.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应用模块名称：电量对象
 * 
 * @author show
 * @since 2020/7/10 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElecDTO {
    /** 今日电量 */
    private String todayElec;
    /** 剩余电量 */
    private String surplusElec;
    /** 本月电量 */
    private String monthElec;

}
