/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.enums;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:37:24
 */
public enum ResponseMessageStatusEnum {

	SUCCESS(200, "成功"),
	ERROR(50000, "错误"),
	NO_DATA(50001, "无数据"),
	ERROR_PAGE(50002, "错误页码");
	
	private Integer code;
	private String message;
	
	private ResponseMessageStatusEnum(Integer code, String message) {
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
