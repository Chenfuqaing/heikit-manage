package com.rejia.manage.model.product;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2020-8-3 9:41:37
 */
@TableName("project")
public class ProjectDO extends Model<ProjectDO> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 *	项目金额
	 */
	private BigDecimal price;
	
	/**
	 *	开票金额
	 */
	private BigDecimal invoicePrice;
	
	/**
	 * 操作人
	 */
	private String operator;
	
	/**
	 * 客户名称
	 */
	private String customerName;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date createDate;
	
	private Date modifyDate;

	public static long getSerialversionuid() {
		return serialVersionUID;
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
