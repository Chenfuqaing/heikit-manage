/**
 * Copyright 2017-2022(c) 浙江嗨乐科技有限公司.All Rights Reserved.
 */
package com.rejia.manage.model.market;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rejia.manage.model.AbstractBaseDO;

/**
 * <P> 
 *	合同项目成本汇总
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-5 13:28:45
 */
@TableName("contract_receivable")
public class ContractReceivableDO extends AbstractBaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
	/**
	 * 编号
	 */
	@TableField("code")
	private String code;
	
	/**
	 * 采购内容
	 */
	@TableField("buy_content")
	private String buyContent;
	
	/**
	 * 合同编号
	 */
	@TableField("contract_code")
	private String contractCode;
	
	/**
	 * 供应商
	 */
	@TableField("supplier")
	private String supplier;
	
	/**
	 * 数量
	 */
	@TableField("number")
	private int number;
	
	/**
	 * 金额
	 */
	@TableField("price")
	private BigDecimal price;
	
	/**
	 * 税率
	 */
	@TableField("tax")
	private BigDecimal tax;
	
	/**
	 * 付款情况
	 */
	@TableField("payment")
	private String payment;
	
	/**
	 * 备注
	 */
	@TableField("message")
	private String message;

}
