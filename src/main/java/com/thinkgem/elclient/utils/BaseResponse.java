package com.thinkgem.elclient.utils;


/**
 * @author zhaoqingjie
 */
public class BaseResponse<T> {
	/**错误码**/
	private Integer code;
	/**提示信息**/
	private String msg;
	/** 返回数据 **/
	private T data;
	
	public BaseResponse(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public BaseResponse() {
		super();
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
