/**
 * 浙江嗨乐科技有限公司
 */
package com.rejia.manage.model.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>TODO</p>
 *
 * @author 姚浩杰
 * 2018年8月17日 下午2:05:50
 *
 */
@TableName("operation_log")
public class OperationLogDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8474621259496239462L;
	
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	
	@TableField("user_id")
	private Integer userId;
	
	@TableField("user_name")
	private String userName;
	
	private String url;
	
	@TableField("http_method")
	private String httpMethod;
	
	@TableField("class_method")
	private String classMethod;
	
	private String ip;
	
	private String args;
	
	private String response;
	
	@TableField("create_date")
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getClassMethod() {
		return classMethod;
	}

	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "OperationLogDO{" +
			"id=" + id +
			", userId=" + userId +
			", userName=" + userName +
			", url=" + url +
			", httpMethod=" + httpMethod +
			", classMethod=" + classMethod +
			", ip=" + ip +
			", args=" + args +
			", response=" + response +
			", createDate=" + createDate +
			"}";
	}
	
}
