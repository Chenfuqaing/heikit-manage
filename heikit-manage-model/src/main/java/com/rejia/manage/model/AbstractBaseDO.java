package com.rejia.manage.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p>TODO</p>
 *
 * @author 姚浩杰
 * 2018年8月7日 下午4:21:26
 *
 */
public class AbstractBaseDO extends Model<AbstractBaseDO>{

	private static final long serialVersionUID = 1L;
	
	@TableField("create_date")
	@Excel(name = "日期", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "2")
	private Date createDate;
	@TableField("modify_date")
	private Date modifyDate;

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}



}
