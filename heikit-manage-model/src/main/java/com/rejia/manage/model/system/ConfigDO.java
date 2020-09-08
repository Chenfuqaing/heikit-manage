package com.rejia.manage.model.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rejia.manage.model.AbstractBaseDO;

/**
 * 
 * <P> 
 *	系统参数配置表
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 16:56:06
 */
@TableName("system_config")
public class ConfigDO extends AbstractBaseDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9174386397612359760L;
	
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	
    /**
     * 标题
     */
	private String name;
	
    /**
     * 键值
     */
	@TableField("config_key")
	private String configKey;
	
    /**
     * 具体值
     */
	private String value;
	
    /**
     * 数据类型
     */
	@TableField("config_type")
	private String configType;
	
	@TableField("status")
	private Integer status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Config{" +
			"id=" + id +
			", name=" + name +
			", configKey=" + configKey +
			", value=" + value +
			", configType=" + configType +
			", createDate=" + getCreateDate() +
			", modifyDate=" + getModifyDate() +
			", status=" + status +
			"}";
	}
}
