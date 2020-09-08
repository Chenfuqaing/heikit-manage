/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller.system;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.dbcore.service.system.SystemResourceService;
import com.rejia.manage.model.system.SystemResourceDO;
import com.rejia.manage.web.controller.AbstractBaseController;

/**
 * 
 * <P> 
 *  菜单 前端控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 10:05:40
 */
@Controller
@RequestMapping("manage/system/resource")
public class ResourceController extends AbstractBaseController<SystemResourceDO>{
	private @Resource SystemResourceService resourceService;
	
	@Autowired
	public ResourceController(SystemResourceService resourceService) {
		super(resourceService);
		this.templatePrefix = "manage/system/resource/";
	}
	
	@Override
	public IPage<SystemResourceDO> getData(HttpServletRequest request, IPage<SystemResourceDO> page) {
		String name = RequestUtil.getStringValue(request, "name");
		String type = RequestUtil.getStringValue(request, "type");
		
		QueryWrapper<SystemResourceDO> wrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(type)) {
			wrapper.eq("type", type);
		}
		if(StringUtils.isNotEmpty(name)) {
			wrapper.like("name", name);
		}
		return resourceService.page(page, wrapper);
	}

	@Override
	public void setRequest(HttpServletRequest request, Model model) {
		Long id = RequestUtil.getValue(request, "id", 0L);
		SystemResourceDO resourceDO = resourceService.getById(id);
		model.addAttribute("resourceDO", resourceDO);
	}
	@Override
	public String preAdd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<SystemResourceDO> list = resourceService.selectByType(0);
		request.setAttribute("faResourceDOs", list);
		SystemResourceDO resource = new SystemResourceDO();
		resource.setStatus(0);
		List<SystemResourceDO> listone = resourceService.selectList(resource);
		request.setAttribute("allResourceDOs", listone);
		return super.preAdd(request);
	}
	@Override
	public SystemResourceDO handleAdd(HttpServletRequest request, SystemResourceDO t) {
		t.setCreateDate(new Date());
		t.setStatus(0);
		return super.handleAdd(request, t);
	}
	@Override
	public  SystemResourceDO handleEdit(HttpServletRequest request, SystemResourceDO resourceDO) {
		Date modifyDate = new Date();
		resourceDO.setModifyDate(modifyDate);
		return resourceDO;
	}
}
