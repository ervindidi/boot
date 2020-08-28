package com.zy.util;

import java.util.HashMap;
import java.util.List;

/**
 * ajax 返回值工具类
 * @author junliu
 */
public class ResultUtil {
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   错误信息
	 * @return
	 */
	public static HashMap<String,Object> getResult(String code,String msg){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   提示信息
	 * @param data  多个的提示信息
	 * @return
	 */
	public static HashMap<String,Object> getResult(String code,String msg,Object data){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   提示信息
	 * @param data  多个的提示信息
	 * @return
	 */
	public static HashMap<String,Object> getResult(String code,String msg,Long count,List data){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   提示信息
	 * @param list  集合的返回数据
	 * @return
	 */
	public static HashMap<String,Object> getResult(String code,String msg,List list){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		map.put("list", list);
		return map;
	}
}
