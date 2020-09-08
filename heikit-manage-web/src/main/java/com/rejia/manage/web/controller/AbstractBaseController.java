/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rejia.manage.common.base.BaseCore;
import com.rejia.manage.common.dto.TableDataDTO;
import com.rejia.manage.common.util.RequestUtil;

/**
 * 
 * <P> 
 *	公共前端控制器抽象类
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:49:50
 */
public abstract class AbstractBaseController<T> extends BaseCore{

	protected String templatePrefix = "";
	private IService<T> iService;
	
	
	public AbstractBaseController(IService<T> iService) {
		this.iService = iService;
	}

	protected Map<String, Object> getMapByRequest(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		Enumeration<?> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
	        String name =(String) paramNames.nextElement();
	        String value = request.getParameter(name);
	        data.put(name, value);
	    }
		return data;
	}
	
	protected IPage<T> getPageData(HttpServletRequest request) {
		int page = RequestUtil.getValue(request, "page", 0);
		int limit = RequestUtil.getValue(request, "limit", 0);
		String column = RequestUtil.getStringValue(request,"column");
		String sort = RequestUtil.getStringValue(request,"sort");
		Page<T> pageData = new Page<>(page, limit);
		
		if (!StringUtils.isEmpty(column)){
			pageData = new Page<T>(page, limit);
			if ("asc".equals(sort)){
				pageData.addOrder(OrderItem.asc(column));
			}else{
				pageData.addOrder(OrderItem.desc(column));
			}
		}else {
			pageData = new Page<T>(page, limit);
			pageData.addOrder(OrderItem.desc("id"));
		}
		return pageData;
	}

	@RequestMapping("list")
	public String list(HttpServletRequest request,Model model) {
		return templatePrefix + "list.btl";
	}
	
	@RequestMapping("pre_add")
	public String preAdd(HttpServletRequest request) {
		return templatePrefix + "pre_add.btl";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResponseEntity<?> add(HttpServletRequest request, T t){
		T finalT = handleAdd(request,t);
		boolean result= iService.save(finalT);
		return result ? createSuccessMessage("操作成功") : createErrorMessage("操作失败");
	}
	
	@RequestMapping("pre_edit")
	public String preEdit(@RequestParam("id") Integer id, HttpServletRequest request, Model model) {
		model.addAttribute("t", iService.getById(id));
		setRequest(request,model);
		return templatePrefix + "pre_edit.btl";
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public ResponseEntity<?> edit(HttpServletRequest request, T t){
		T finalT = handleEdit(request,t);
		boolean  result = iService.updateById(finalT);
		
		return result ? createSuccessMessage("操作成功") : createErrorMessage("操作失败");
	}
	
	@RequestMapping("get_json")
	public ResponseEntity<?> getData(HttpServletRequest request,T t){
		IPage<T> pageRequest = getPageData(request);
		IPage<T> resultPage = getData(request, pageRequest);
		return ResponseEntity.ok(parseTableData(resultPage));
	}
	
	public abstract IPage<T> getData(HttpServletRequest request, IPage<T> pageRequest);
	
	public abstract void setRequest(HttpServletRequest request, Model model);
	
	public  T handleAdd(HttpServletRequest request, T t) {
		return t;
	}
	
	public  T handleEdit(HttpServletRequest request, T t) {
		return t;
	}
	
	private TableDataDTO parseTableData(IPage<T> resultPage) {
		if(resultPage == null) {
			return new TableDataDTO(0, null);
		}
		
		int count = Integer.parseInt(resultPage.getTotal()+"");
		return new TableDataDTO(count, resultPage.getRecords());
	}
}
