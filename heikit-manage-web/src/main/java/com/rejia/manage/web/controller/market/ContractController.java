/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller.market;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rejia.manage.common.util.CodeUtil;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.dbcore.service.market.ContractService;
import com.rejia.manage.model.market.ContractDO;
import com.rejia.manage.web.controller.AbstractBaseController;

/**
 * <P> 
 *	合同前段控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-5 13:39:29
 */
@Controller
@RequestMapping("manage/market/contract")
public class ContractController extends AbstractBaseController<ContractDO> {

	private @Resource ContractService contractService;
	/**
	 * @param iService
	 */
	public ContractController(ContractService contractService) {
		super(contractService);
		this.templatePrefix = "manage/market/contract/";
	}

	@Override
	public IPage<ContractDO> getData(HttpServletRequest request, IPage<ContractDO> pageRequest) {
		String projectName = RequestUtil.getStringValue(request, "project_name");
		String signDateStart = RequestUtil.getStringValue(request, "sign_date_start");
		String signDateEnd = RequestUtil.getStringValue(request, "sign_date_end");
		
		QueryWrapper<ContractDO> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotBlank(projectName)) {
			queryWrapper.like("project_name", projectName);
		}
		if(!StringUtils.isAnyEmpty(signDateStart, signDateEnd)) {
			queryWrapper.ge("sign_date", signDateStart);
			queryWrapper.le("sign_date", signDateEnd);
		}
		return contractService.page(pageRequest, queryWrapper);
	}

	@Override
	public void setRequest(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public ContractDO handleAdd(HttpServletRequest request, ContractDO contractDO) {

		Date createDate = new Date();
		contractDO.setCreateDate(createDate);
	    String code = CodeUtil.generate("HT");
	    contractDO.setCode(code);
		
		return contractDO;
	}
	
	@Override
	public ContractDO handleEdit(HttpServletRequest request, ContractDO contractDO) {
		Date modifyDate = new Date();
		contractDO.setModifyDate(modifyDate);
		
		return contractDO;
	}
	
}
