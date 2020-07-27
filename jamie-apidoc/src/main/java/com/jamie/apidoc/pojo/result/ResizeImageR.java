package com.jamie.apidoc.pojo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 压缩图片返回对象
 * 
 * @author xuanweiyao
 * @date 2020/7/21 16:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "压缩图片返回对象")
public class ResizeImageR {

    @ApiModelProperty(value = "压缩后图片base64", name = "base64Img", example = "iVBORw0KGgoAAAANSUhE...", required = true)
    private String base64Img;
}
