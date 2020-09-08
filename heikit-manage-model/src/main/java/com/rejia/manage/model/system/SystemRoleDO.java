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
 *	用户角色表
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:41:50
 */
@TableName("system_role")
public class SystemRoleDO extends Model<SystemRoleDO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7932660062159802478L;
	
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
