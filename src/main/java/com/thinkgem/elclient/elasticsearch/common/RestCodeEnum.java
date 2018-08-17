package com.thinkgem.elclient.elasticsearch.common;

/**
 * <p>rest 状态枚举类</p>
 */
public enum RestCodeEnum {
    SUCCESS(200, "请求成功"), NOT_FOUND(500, "服务器错误"), PARAMS_ERROR(400, "请求参数错误");

    private Integer code;
    private String desc;

    RestCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
