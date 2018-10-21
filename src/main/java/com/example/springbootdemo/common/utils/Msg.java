package com.example.springbootdemo.common.utils;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 * 
 * @author yq
 * 
 */

public class Msg {
	//状态码   200-成功    404-失败
	private final int code;
	//提示信息
	private final String msg;
	
	private Msg(int code,String msg){
		this.code = code;
		this.msg = msg;
	};
	
	//用户要返回给浏览器的数据
	private final Map<String, Object> extend = new HashMap<String, Object>();

	public static Msg success(){
		return new Msg(200,"处理成功！");
	}
	
	public static Msg notFind(){
		return new Msg(404,"资源找不到！");
	}

	public static Msg passwordError(){
		return new Msg(4011,"密码错误！");
	}

	public static Msg usernameError(){
		return new Msg(4010,"用户名错误！");
	}

	public static Msg checkCodeError(){
		return new Msg(4012,"验证码错误！");
	}

	public static Msg fail(){
		return new Msg(500,"处理失败！");
	}

	public Msg add(final String key,final Object value){
		extend.put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Map<String, Object> getExtend() {
		return Collections.unmodifiableMap(extend);
	}

}
