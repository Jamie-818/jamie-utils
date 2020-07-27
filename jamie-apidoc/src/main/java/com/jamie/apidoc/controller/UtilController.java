package com.jamie.apidoc.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamie.apidoc.pojo.base.R;
import com.jamie.apidoc.pojo.result.ResizeImageR;
import com.jamie.apidoc.pojo.vo.ResizeImageVO;
import com.jamie.apidoc.utils.ImageUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 工具接口
 * 
 * @author xuanweiyao
 * @date 2020/7/21 9:44
 */
@Slf4j
@RestController
@RequestMapping("/utils")
@Api(tags = "工具模块")
public class UtilController {
    /**
     * 向营销查询工单证件信息
     */
    @ApiOperation(value = "压缩图片", notes = "base压缩图片", httpMethod = "POST")
    @PostMapping(value = "/resizeImage")
    public R<ResizeImageR> resizeImage(@Validated @RequestBody ResizeImageVO vo) {
        String base64Img = vo.getBase64Img();
        Integer sizeRatio = vo.getSizeRatio();
        Integer widthRatio = vo.getWidthRatio();
        Integer heightRatio = vo.getHeightRatio();
        String resizeBase = ImageUtils.resizeImage(base64Img, sizeRatio, widthRatio, heightRatio);
        return R.success(ResizeImageR.builder().base64Img(resizeBase).build());
    }

}
