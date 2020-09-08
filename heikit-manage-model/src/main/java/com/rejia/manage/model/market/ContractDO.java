package com.rejia.manage.model.market;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rejia.manage.model.AbstractBaseDO;

/**
 * <P> 
 *	合同表
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-5 13:08:03
 */
@TableName("contract")
public class ContractDO extends AbstractBaseDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	
	/**
	 * 合同编码
	 */
	@TableField("code")
	private String code;
	
	/**
	 * 合同签订时间
	 */
	@TableField("sign_date")
	private Date signDate;
	
	/**
	 *	签订单位
	 */
	@TableField("company_name")
	private String companyName;
	
	/**
	 *  项目名称
	 */
	@TableField("project_name")
	private String projectName;
	
	/**
	 * 合同内容
	 */
	@TableField("content")
	private String content;
	
	/**
	 * 状态
	 */
	@TableField("status")
	private int status;
	
	/**
	 * 合同金额
	 */
	@TableField("price")
	private BigDecimal price;
	
	/**
	 * 税率
	 */
	@TableField("tax")
	private BigDecimal tax;
	
	/**
	 * 付款方式
	 */
	@TableField("pay_method")
	private String payMethod;
	
	/**
	 * 已收款
	 */
	@TableField("receive_price")
	private BigDecimal receivePrice;
	
	/**
	 * 应收款
	 */
	@TableField("receivable_price")
	private BigDecimal receivablePrice;
	
	/**
	 * 项业务员
	 */
	@TableField("user_id")
	private String userId;
	
	/**
	 * 质保期
	 */
	@TableField("warranty_time")
	private Date warrantyTime;
	
	/**
	 * 合同交货期
	 */
	@TableField("appoint_delivery_time")
	private String appointDeliveryTime;
	
	/**
	 * 实际交货期
	 */
	@TableField("actual_delivery_time")
	private String actualDeliveryTime;
	
	/**
	 * 已开票
	 */
	@TableField("gain_invoice")
	private BigDecimal gainInvoice;
	
	/**
	 * 未开票
	 */
	@TableField("without_invoice")
	private BigDecimal withoutInvoice;
	
	/**
	 * 合同类型：0:销售、1:配件、2:售后、3:改造、4:其它
	 */
	@TableField("type")
	private int type;
	
	/**
	 * 项目地址
	 */
	@TableField("address")
	private String address;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the signDate
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * @param signDate the signDate to set
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return the receivePrice
	 */
	public BigDecimal getReceivePrice() {
		return receivePrice;
	}

	/**
	 * @param receivePrice the receivePrice to set
	 */
	public void setReceivePrice(BigDecimal receivePrice) {
		this.receivePrice = receivePrice;
	}

	/**
	 * @return the receivablePrice
	 */
	public BigDecimal getReceivablePrice() {
		return receivablePrice;
	}

	/**
	 * @param receivablePrice the receivablePrice to set
	 */
	public void setReceivablePrice(BigDecimal receivablePrice) {
		this.receivablePrice = receivablePrice;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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

	/**
	 * @return the tax
	 */
	public BigDecimal getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	/**
	 * @return the warrantyTime
	 */
	public Date getWarrantyTime() {
		return warrantyTime;
	}

	/**
	 * @param warrantyTime the warrantyTime to set
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setWarrantyTime(Date warrantyTime) {
		this.warrantyTime = warrantyTime;
	}

	/**
	 * @return the appointDeliveryTime
	 */
	public String getAppointDeliveryTime() {
		return appointDeliveryTime;
	}

	/**
	 * @param appointDeliveryTime the appointDeliveryTime to set
	 */
	public void setAppointDeliveryTime(String appointDeliveryTime) {
		this.appointDeliveryTime = appointDeliveryTime;
	}

	/**
	 * @return the actualDeliveryTime
	 */
	public String getActualDeliveryTime() {
		return actualDeliveryTime;
	}

	/**
	 * @param actualDeliveryTime the actualDeliveryTime to set
	 */
	public void setActualDeliveryTime(String actualDeliveryTime) {
		this.actualDeliveryTime = actualDeliveryTime;
	}

	/**
	 * @return the gainInvoice
	 */
	public BigDecimal getGainInvoice() {
		return gainInvoice;
	}

	/**
	 * @param gainInvoice the gainInvoice to set
	 */
	public void setGainInvoice(BigDecimal gainInvoice) {
		this.gainInvoice = gainInvoice;
	}

	/**
	 * @return the withoutInvoice
	 */
	public BigDecimal getWithoutInvoice() {
		return withoutInvoice;
	}

	/**
	 * @param withoutInvoice the withoutInvoice to set
	 */
	public void setWithoutInvoice(BigDecimal withoutInvoice) {
		this.withoutInvoice = withoutInvoice;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
