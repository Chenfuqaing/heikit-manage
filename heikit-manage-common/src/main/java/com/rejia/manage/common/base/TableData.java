/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.base;

/**
 * <p>layuiTable</p>
 *
 * @author 姚浩杰
 * 2018年7月6日 上午11:14:48
 *
 */
public class TableData {
	private int code;
	private String msg;
	private int count;
	private Object data;
	
	
	public TableData() {
	}
	
	public TableData(int code, String msg, int count, Object data) {
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public TableData(int count, Object data) {
		this(0, "", count, data);
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
