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
 *	角色资源表
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:48:23
 */
@TableName("system_role_resource")
public class SystemRoleResourceDO extends Model<SystemRoleResourceDO>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4263164265154721604L;
	
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
	@TableField("role_id")
	private Long roleId;
	
	@TableField("resource_id")
	private Long resourceId;
	
    /**
     * 资源权限标记
     */
	private String permission;
	
    /**
     * 操作权限标记
     */
	private String operate;
	
	@TableField("order_num")
	private Integer orderNum;
	
	@TableField("is_parent")
	private String isParent;
	
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}


	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
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

	/**
	 * @return the orderNum
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	
}
