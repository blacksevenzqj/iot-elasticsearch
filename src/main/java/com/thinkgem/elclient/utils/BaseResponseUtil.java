package com.thinkgem.elclient.utils;

import com.thinkgem.elclient.config.enums.ResultEnum;

/**
 * @author zhaoqingjie
 */
public class BaseResponseUtil {

	public static BaseResponse success(){
		return success(null);
	}

	public static BaseResponse success(Object object){
		BaseResponse response=new BaseResponse();
		response.setData(object);
		response.setCode(ResultEnum.SUCCESS.getCode());
		response.setMsg(ResultEnum.SUCCESS.getMessage());
		return response;
	}

	public static BaseResponse success(Integer code, String msg, Object object){
		BaseResponse response=new BaseResponse();
		response.setData(object);
		response.setCode(code);
		response.setMsg(msg);
		return response;
	}
	
	public static BaseResponse error(Integer code,String msg){
		BaseResponse response=new BaseResponse();
		response.setCode(code);
		response.setMsg(msg);
		return response;
	}


}

