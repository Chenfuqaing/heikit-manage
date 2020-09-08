/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * 
 * <P> 
 * 文件标题: RequestUtil<br>
 * 文件说明: request请求工具<br>
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 10:00:51
 */
public class RequestUtil {
	/**
	 * 从request中获取int值
	 * @param request http请求
	 * @param name 参数名
	 * @param defaultValue 默认值
	 * @return 获取结果值
	 */
	public static Integer getIntValue(HttpServletRequest request, String name, Integer defaultValue){
		String value = request.getParameter(name);
		if (StringUtils.isNumeric(value)) {
			return Integer.parseInt(value);
		}
		return defaultValue;
	}
	
	public static Integer getValue(HttpServletRequest request, String name, Integer defaultValue){
		String value = request.getParameter(name);
		if (StringUtils.isNumeric(value)) {
			return Integer.parseInt(value);
		}
		return defaultValue;
	}
	
	public static Long getValue(HttpServletRequest request, String name, Long defaultValue){
		String value = request.getParameter(name);
		if (StringUtils.isNumeric(value)) {
			return Long.parseLong(value);
		}
		return defaultValue;
	}
	
	public static String getValue(HttpServletRequest request, String name, String defaultValue){
		String value = request.getParameter(name);
		return value==null ? defaultValue : value;
	}
	
	public static String getStringValue(HttpServletRequest request, String name){
		return request.getParameter(name);
	}
	
	/**
	 * 获取数组
	 * @param request http请求
	 * @param name 参数
	 * @return 结果数组
	 */
	public static String[] getValues(HttpServletRequest request, String name){
		return request.getParameterValues(name);
	}
	
	/**
	 * 根据request获取基础url
	 * @param request
	 * @return
	 */
	public static String getBaseUrl(HttpServletRequest request){
		String baseUrl = "";
		StringBuilder url = new StringBuilder();
		url.append(request.getScheme()).append("://")
		   .append(request.getServerName());
		int port = request.getServerPort();
		if (port!=80) {
			url.append(":").append(port);
		}
		url.append(request.getContextPath());
		baseUrl = url.toString();
		return baseUrl;
	}
	
	/**
	 * 从请求地址中获取请求参数
	 * @param urlParams 请求地址
	 * @return 参数map集合
	 */
	public static Map<String, Object> getUrlParams(String urlParams){
		if (StringUtils.isEmpty(urlParams)) {
			return null;
		}
		
		if (!StringUtils.contains(urlParams, "?")) {
			return null;
		}
		
		String paramsString = StringUtils.split(urlParams, "?")[1];
		String[] paramArray = StringUtils.split(paramsString, "&");
		if (paramArray==null || paramArray.length==0) {
			return null;
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		for (String values : paramArray) {
			String[] valueArray = StringUtils.split(values, "=");
			if (valueArray==null || valueArray.length!=2) {
				continue;
			}
			
			resultMap.put(valueArray[0], valueArray[1]);
		}
		
		return resultMap;
	}
}
