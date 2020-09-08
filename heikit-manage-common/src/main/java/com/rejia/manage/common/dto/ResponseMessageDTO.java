/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.rejia.manage.common.enums.ResponseMessageStatusEnum;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:37:04
 */
public class ResponseMessageDTO {
	private boolean hasError = false;
	private boolean tokenError = false;
	private String message = "成功";
	
	private ResponseMessageStatusEnum status;
	private Integer code;
	
	private Map<String, Object> data;

	public ResponseMessageDTO() { 
		this.status = ResponseMessageStatusEnum.SUCCESS;
	}
	
	public ResponseMessageDTO(ResponseMessageStatusEnum status, String message) {
		this.status = status;
		this.message = message;
		if (status.equals(ResponseMessageStatusEnum.SUCCESS)) {
			this.hasError = false;
		}else {
			this.hasError = true;
		}
		this.code = status.getCode();
	}

	public boolean getHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public boolean isTokenError() {
		return tokenError;
	}

	public void setTokenError(boolean tokenError) {
		this.tokenError = tokenError;
	}

	public String getMessage() {
		if (StringUtils.isNotEmpty(message)) {
			return message;
		}
		
		if (status != null) {
			return status.getMessage();
		}
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public ResponseMessageStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(ResponseMessageStatusEnum status) {
		this.status = status;
	}
	
	public Integer getCode() {
		if (status != null) {
			this.code = status.getCode();
		}
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public void put(String key, Object value) {
		if (data == null) {
			data = new HashMap<>();
		}
		
		data.put(key, value);
	}
}
