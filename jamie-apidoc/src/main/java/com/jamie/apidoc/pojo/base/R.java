package com.jamie.apidoc.pojo.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamie.apidoc.enums.RCodeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 前端交互对象
 *
 * @author xuanweiyao
 * @date 15:40 2018/9/18
 */
@ToString
@ApiModel(value = "全局返回对象")
public class R<T> implements Serializable {
    /**
     * 返回结果状态码标志
     */
    @Getter
    @ApiModelProperty(value = "返回错误码，00为成功，02为全局异常", example = "00", required = true)
    private String sta;
    /**
     * 返回消息标志
     */
    @Getter
    @ApiModelProperty(value = "返回错误错误信息")
    private String message;
    /**
     * 返回结果标志
     */
    @Getter
    @ApiModelProperty(value = "返回对象")
    private T data;

    /**
     * 让这个字JSON序列化以后，不显示在JSON里面 使之不在序列化结果当中
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.sta.equals(RCodeEnums.SUCCESS.getCode());
    }

    /**
     * 私有构造器
     */
    private R(String status) {
        this.sta = status;
    }

    private R(String status, T data) {
        this.sta = status;
        this.data = data;
    }

    private R(String status, String message, T data) {
        this.sta = status;
        this.message = message;
        this.data = data;
    }

    private R(String status, String message) {
        this.sta = status;
        this.message = message;
    }

    /**
     * 成功的构造器
     */
    public static <T> R<T> success() {
        return new R<>(RCodeEnums.SUCCESS.getCode());
    }

    public static <T> R<T> successMessage(String message) {
        return new R<>(RCodeEnums.SUCCESS.getCode(), message);
    }

    public static <T> R<T> success(T data) {
        return new R<>(RCodeEnums.SUCCESS.getCode(), data);
    }

    public static <T> R<T> success(String message, T data) {
        return new R<>(RCodeEnums.SUCCESS.getCode(), message, data);
    }

    public static <T> R<T> resultCodeMsgData(String code, String message, T data) {
        return new R<>(code, message, data);
    }

    // public static <T> ServerResponse<T> successCodeData(String code, T data) {
    // return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), "", data);
    // }

    public static <T> R<T> resultCodeMsgData(String code, String message) {

        return new R<>(code, message);
    }

    /**
     * 失败的构造器
     */

    public static <T> R<T> errorStack(String errorMessage, T data) {

        return new R<>(RCodeEnums.SYSTEM_ERROR.getCode(), errorMessage, data);
    }

    public static <T> R<T> codeMessage(String code, String message) {

        return new R<>(code, message);
    }

    public static <T> R<T> msError(String errorMessage) {

        return new R<>(RCodeEnums.MS_ERROR.getCode(), errorMessage);
    }

    public static <T> R<T> paramsError(String errorMessage) {

        return new R<>(RCodeEnums.PARAMS_ERROR.getCode(), errorMessage);
    }

    public static <T> R<T> systemError(String errorMessage) {

        return new R<>(RCodeEnums.SYSTEM_ERROR.getCode(), errorMessage);
    }

    public static <T> R<T> msAppResponseToR(MsAppResponse response, Class<T> tClass) {
        if (response == null) {
            return R.msError("response is null");
        } else if (response.getSta() == null) {
            return R.msError("调用接口异常,状态码返回为null");
        } else if (StringUtils.equals(RCodeEnums.SUCCESS.getCode(), response.getSta())) {
            String objJson = JSON.toJSONString(response.getData());
            T object = JSON.parseObject(objJson, tClass);
            return R.resultCodeMsgData(response.getSta(), response.getMessage(), object);
        } else {
            return R.resultCodeMsgData(response.getSta(), response.getMessage());
        }
    }

    public static <T> R<List<T>> msAppResponseToRList(MsAppResponse response, Class<T> tClass) {
        if (response == null) {
            return R.msError("response is null");
        } else if (response.getSta() == null) {
            return R.msError("调用接口异常,状态码返回为null");
        } else if (StringUtils.equals(RCodeEnums.SUCCESS.getCode(), response.getSta())) {
            String objJson = JSON.toJSONString(response.getData());
            List<T> object = JSON.parseArray(objJson, tClass);
            return R.resultCodeMsgData(response.getSta(), response.getMessage(), object);
        } else {
            return R.resultCodeMsgData(response.getSta(), response.getMessage());
        }
    }
}
