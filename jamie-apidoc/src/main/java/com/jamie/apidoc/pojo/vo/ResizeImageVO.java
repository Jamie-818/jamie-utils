package com.jamie.apidoc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 压缩图片入参
 *
 * @author xuanweiyao
 * @date 2020/7/21 10:05
 */
@Data
@ApiModel(value = "压缩图片入参", description = "前端压缩图片入参")
public class ResizeImageVO {
    @ApiModelProperty(value = "图片base64", name = "base64Img", example = "iVBORw0KGgoAAAANSUhE...", required = true)
    @NotBlank(message = "图片base64 base64Img 不能为空")
    private String base64Img;

    @ApiModelProperty(value = "大小压缩比率,传2代表0.5", name = "sizeRatio", example = "2", required = true)
    @Min(value = 1, message = "sizeRatio 必须为正整数")

    private Integer sizeRatio;

    @ApiModelProperty(value = "宽度压缩比率,传2代表0.5", name = "widthRatio", example = "2", required = true)
    @Min(value = 1, message = "widthRatio 必须为正整数")
    @NotNull(message = "widthRatio 不能为空")
    private Integer widthRatio;

    @ApiModelProperty(value = "长度压缩比率,传2代表0.5", name = "heightRatio", example = "2", required = true)
    @Min(value = 1, message = "heightRatio 必须为正整数")
    @NotNull(message = "heightRatio 不能为空")
    private Integer heightRatio;

}
