package com.rejia.manage.model.system;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:41:44
 */
@TableName("system_resource")
public class SystemResourceDO extends Model<SystemResourceDO>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3496436879922322919L;
	
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
	@TableField("parent_id")
	private Long parentId;
	
	private String name;
	
	private String url;
	
//	private String perms;
	
	private Integer type;
	
	private String icon;
	
	@TableField("order_num")
	private Integer orderNum;
	
	@TableField("status")
	private int status;

	@TableField("create_date")
	private Date createDate;
	
	@TableField("modify_date")
	private Date modifyDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
//
//	public String getPerms() {
//		return perms;
//	}
//
//	public void setPerms(String perms) {
//		this.perms = perms;
//	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	

}
