package com.thinkgem.elclient.config.enums;


/**
 * @author zhaoqingjie
 */
public enum ResultEnum {
	LOGIN_TOKEN_ERROR(1,"身份认证异常，请检查并重新登陆"),//登陆token异常
	LOGIN_TOKEN_INVALID(2,"身份认证过期，请重新登陆"),//登陆token过期,请重新登陆
	USER_NOT_EXIST(3,"用户不存在"),
	LOGIN_PASSWORD_ERROR(4,"登陆密码错误"),
	NO_FARMER_LOGIN(5,"非农户账户不能登录APP"),
	NO_FARMER_CHANGEPASSWORD(6,"非农户账户不能修改密码"),
	OLD_PASSWORD_ERROR(7,"原密码错误"),
	CHANGE_PASSWORD_SUCCESS(8,"密码修改成功"),
	CACHE_SERVER_EXCEPTION(9, "缓存服务器异常"),
	SUCCESS(200, "请求成功"),
	ERROR(500,"后台处理异常"),
	;
	
	private Integer code;
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}


