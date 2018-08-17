package com.thinkgem.elclient.entity;

import com.thinkgem.elclient.config.ResultCodeConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装返回json对象
 */
public class Result {

	/**返回码  */
	private   int code;  
	/**提示信息*/
	private   String msg;

	private static int successCode= ResultCodeConstants.RE_CODE_200;

	private static int failCode=ResultCodeConstants.RE_CODE_403;
	/**返回的数据*/
	private  Map<Object, Object> data=new HashMap<Object, Object>();

	public int getCode() {
		return code;
	}

	/**
	 * 添加返回码
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}

	/**
	 * 添加返回提示信息
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<Object, Object> getData() {
		return data;
	}

	/**
	 * 添加返回数据
	 * @param key
	 * @param value
	 */
	public void setData(Object key,Object value) {
		this.data.put(key, value);
	}

	/**
	 * 可以连接追加调用此方法，添加返回数据
	 * @param key
	 * @param value
	 * @return
	 */
	public Result add(Object key,Object value){
		this.data.put(key, value);
		return this;
	}

	/**
	 * 返回执行成功
	 * @return
	 */
	public static Result success () {
		Result result=new Result();
		result.setCode(successCode);
		result.setMsg("请求成功");
		return result;
	}

	/**
	 * 返回执行失败
	 * @return
	 */
	public static Result fail() {
		Result result=new Result();
		result.setCode(failCode);
		result.setMsg("请求失败");
		return result;
	}

	/**
	 * 自定义返回结果
	 * @param code  返回码
	 * @param msg   返回提示信息
	 * @return
	 */
	public static Result ret(int code,String msg) {
		Result result=new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 请求成功，添加返回数据
	 * @param key  
	 * @param value
	 * @return
	 */
	public static Result success(Object key, Object value) {
		return Result.success().add(key, value);
	}

	/**
	 * 返回成功
	 * @param msg 自定义成功提示信息
	 * @return
	 */
	public static Result success(String msg) {
		return Result.ret(successCode, msg);
	}

	/**
	 * 返回失败
	 * @param msg 自定义失败原因
	 * @return
	 */
	public static Result fail(String msg) {
		return Result.ret(failCode, msg);
	}

	/**
	 * 返回失败，自定义返回数据
	 * @param key
	 * @param value
	 * @return
	 */
	public static Result fail(Object key, Object value) {
		return Result.fail().add(key, value);
	}
}
