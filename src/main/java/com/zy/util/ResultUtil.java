package com.zy.util;

import com.zy.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * ajax 返回值工具类
 * @author junliu
 */
public class ResultUtil {
	private static final Logger log = LoggerFactory.getLogger(ResultUtil.class);
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   错误信息
	 * @return
	 */
	public static HashMap<String,Object> getResult(HttpServletRequest request, String code, String msg){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);

		log.info("请求地址："+request.getRequestURI()+"   返回码 : "+code + "   返回信息 : "+msg);
		return map;
	}
	
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   提示信息
	 * @param data  多个的提示信息
	 * @return
	 */
	public static HashMap<String,Object> getResult(HttpServletRequest request,String code,String msg,Object data){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);

		log.info("请求地址："+request.getRequestURI()+"   返回码 : "+code + "   返回信息 : "+msg);
		return map;
	}
	
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   提示信息
	 * @param data  多个的提示信息
	 * @return
	 */
	public static HashMap<String,Object> getResult(HttpServletRequest request,String code,String msg,Long count,List data){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);

		log.info("请求地址："+request.getRequestURI()+"   返回码 : "+code + "   返回信息 : "+msg);
		return map;
	}
	
	/**
	 * 
	 * @param code  成功为 0/1  失败 为错误编码
	 * @param msg   提示信息
	 * @param list  集合的返回数据
	 * @return
	 */
	public static HashMap<String,Object> getResult(HttpServletRequest request,String code,String msg,List list){
		HashMap<String,Object> map = new HashMap<String,Object> ();
		map.put("code", code);
		map.put("msg", msg);
		map.put("list", list);

		log.info("请求地址："+request.getRequestURI()+"   返回码 : "+code + "   返回信息 : "+msg);
		return map;
	}
}
