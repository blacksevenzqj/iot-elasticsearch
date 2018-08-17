package com.thinkgem.elclient.elasticsearch.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * <p>rest api 结果</p>
 */
@Data
public class RestResult<T> {

    private int code;

    private String desc;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public void setRestCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public void setRestCodeEnum(RestCodeEnum restCodeEnum) {
        this.setRestCode(restCodeEnum.getCode(), restCodeEnum.getDesc());
    }

    public static <T> RestResult<T> getSuccessResult() {
        RestResult<T> restResult = new RestResult<>();
        restResult.setRestCodeEnum(RestCodeEnum.SUCCESS);
        return restResult;
    }

    public static <T> RestResult<T> getSuccessResult(T data) {
        RestResult<T> restResult = new RestResult<>();
        restResult.setRestCodeEnum(RestCodeEnum.SUCCESS);
        restResult.setData(data);
        return restResult;
    }

    public static RestResult getFailResult(int code, String desc) {
        RestResult restResult = new RestResult();
        restResult.setRestCode(code, desc);
        return restResult;
    }

    public static RestResult getFailResult(RestCodeEnum restCodeEnum) {
        return getFailResult(restCodeEnum.getCode(), restCodeEnum.getDesc());
    }

}
