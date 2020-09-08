package com.rejia.manage.model.finance;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <P> 
 *	报销报表
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 17:44:35
 */
@TableName("apply")
public class ApplyDO extends Model<ApplyDO> {
	
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
	 * 报销人id
	 */
	@TableField("apply_user_id")
	private String applyUserId;
	
	/**
	 * 报销人
	 */
	@TableField("apply_user_name")
	private String applyUserName;
	
	/**
	 * 申报金额
	 */
	@TableField("price")
	private BigDecimal price;
	
	/**
	 * 实报金额
	 */
	@TableField("gain_price")
	private BigDecimal gainPrice;
	
	/**
	 * 报销日期
	 */
	@TableField("apply_date")
	private Date applyDate;
	
	/**
	 * 票据数量
	 */
	@TableField("number")
	private int number;
	
	/**
	 * 报销内容
	 */
	@TableField("apply_content")
	private String applyContent;
	
	/**
	 * 项目名称
	 */
	@TableField("project_name")
	private String projectName;
	
	/**
	 * 费用归属
	 */
	@TableField("cost_user")
	private String costUser;
	
	/**
	 * 备注信息
	 */
	@TableField("message")
	private String message;
	
	/**
	 * 出差地点
	 */
	@TableField("address")
	private String address;
	
	
	/**
	 * 同行人
	 */
	@TableField("together_user")
	private String togetherUser;
	
	/**
	 * 费用归属
	 */
	@TableField("status")
	private int status;
	
	@TableField("create_date")
	private Date createDate;
	
	@TableField("modify_date")
	private Date modifyDate;
	
	/**
	 * 拒绝原因
	 */
	@TableField("refuse_reason")
	private String refuseReason;
	
	/**
	 * 操作员
	 */
	@TableField("operator")
	private String operator;
	
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
	 * @return the applyDate
	 */
	public Date getApplyDate() {
		return applyDate;
	}

	/**
	 * @param applyDate the applyDate to set
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the applyContent
	 */
	public String getApplyContent() {
		return applyContent;
	}

	/**
	 * @param applyContent the applyContent to set
	 */
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
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
	 * @return the costUser
	 */
	public String getCostUser() {
		return costUser;
	}

	/**
	 * @param costUser the costUser to set
	 */
	public void setCostUser(String costUser) {
		this.costUser = costUser;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the gainPrice
	 */
	public BigDecimal getGainPrice() {
		return gainPrice;
	}

	/**
	 * @param gainPrice the gainPrice to set
	 */
	public void setGainPrice(BigDecimal gainPrice) {
		this.gainPrice = gainPrice;
	}


	/**
	 * @return the applyUserId
	 */
	public String getApplyUserId() {
		return applyUserId;
	}

	/**
	 * @param applyUserId the applyUserId to set
	 */
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
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

	/**
	 * @return the togetherUser
	 */
	public String getTogetherUser() {
		return togetherUser;
	}

	/**
	 * @param togetherUser the togetherUser to set
	 */
	public void setTogetherUser(String togetherUser) {
		this.togetherUser = togetherUser;
	}

	/**
	 * @return the refuseReason
	 */
	public String getRefuseReason() {
		return refuseReason;
	}

	/**
	 * @param refuseReason the refuseReason to set
	 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the applyUserName
	 */
	public String getApplyUserName() {
		return applyUserName;
	}

	/**
	 * @param applyUserName the applyUserName to set
	 */
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	
	
	

}
