/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller.system;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rejia.manage.common.dto.JsonResult;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.dbcore.service.system.SystemResourceService;
import com.rejia.manage.dbcore.service.system.SystemRoleResourceService;
import com.rejia.manage.dbcore.service.system.SystemRoleService;
import com.rejia.manage.model.system.SystemResourceDO;
import com.rejia.manage.model.system.SystemRoleDO;
import com.rejia.manage.model.system.SystemRoleResourceDO;
import com.rejia.manage.web.controller.AbstractBaseController;

/**
 * 
 * <P> 
 *  用户角色 前端控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 10:06:00
 */
@Controller
@RequestMapping("manage/system/role")
public class RoleController extends AbstractBaseController<SystemRoleDO>{
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	private @Resource SystemRoleService roleService;
	private @Resource SystemResourceService resourceService;
	private @Resource SystemRoleResourceService roleResourceService;
	
	@Autowired
	public RoleController(SystemRoleService roleService) {
		super(roleService);
		this.templatePrefix = "manage/system/role/";
	}

	@Override
	public IPage<SystemRoleDO> getData(HttpServletRequest request, IPage<SystemRoleDO> page) {
		String name = RequestUtil.getStringValue(request, "name");
		
		QueryWrapper<SystemRoleDO> wrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(name)) {
			wrapper.like("name", name);
		}
		return roleService.page(page, wrapper);
	}
	
	@RequestMapping("add_page")
	public String add(Model model) {
		SystemResourceDO resource = new SystemResourceDO();
		resource.setType(0);
		List<SystemResourceDO> faResourceDOs = resourceService.selectList(resource);
		
		resource.setType(1);
		List<SystemResourceDO> sonResourceDOs = resourceService.selectList(resource);
		model.addAttribute("faResourceDOs", faResourceDOs);
		model.addAttribute("sonResourceDOs", sonResourceDOs);
		return templatePrefix + "pre_add.btl";
	}
	
	@Override
	public void setRequest(HttpServletRequest request, Model model) {
		SystemResourceDO resource = new SystemResourceDO();
		resource.setType(0);
		List<SystemResourceDO> faResourceDOs = resourceService.selectList(resource);
		
		resource.setType(1);
		List<SystemResourceDO> sonResourceDOs = resourceService.selectList(resource);
		
		Long id = RequestUtil.getValue(request, "id", 0L);
		SystemRoleResourceDO roleResourceDO = new SystemRoleResourceDO();
		roleResourceDO.setRoleId(id);
		
		List<SystemRoleResourceDO> roleResourceDOs = roleResourceService.list(new QueryWrapper<SystemRoleResourceDO>(roleResourceDO));
		List<Long> resourceIds = new ArrayList<>();
		for(SystemRoleResourceDO roleResource:roleResourceDOs) {
			resourceIds.add(roleResource.getResourceId());
		}
		
		SystemRoleDO roleDO = roleService.getById(id);
		
		model.addAttribute("roleDO", roleDO);
		model.addAttribute("faResourceDOs", faResourceDOs);
		model.addAttribute("sonResourceDOs", sonResourceDOs);
		model.addAttribute("resourceIds", resourceIds);
		model.addAttribute("roleResourceDOs", roleResourceDOs);
	}

	@Override
	public ResponseEntity<?> add(HttpServletRequest request, SystemRoleDO roleDO){
		QueryWrapper<SystemRoleDO> wrapper = new QueryWrapper<SystemRoleDO>(roleDO);
		SystemRoleDO role = roleService.getOne(wrapper);
		if(role != null) {
			LOGGER.error("角色已存在,不可重复添加");
			JsonResult jsonResult = new JsonResult();
			jsonResult.setMsg("角色已存在");
			jsonResult.setResult(false);
			return ResponseEntity.ok(jsonResult);
		}
		
		Date createDate = new Date();
		roleDO.setCreateDate(createDate);
		roleService.save(roleDO);
		String[] faResourceId = RequestUtil.getStringValue(request, "faResourceId").split(",");
		String[] sonResourceId = RequestUtil.getStringValue(request, "sonResourceId").split(",");
		role = roleService.getOne(wrapper);
		
		SystemRoleResourceDO roleResourceDO = null;
		List<SystemRoleResourceDO> roleResourceDOs = new ArrayList<>();
		for(String resourceId:faResourceId) {
			SystemResourceDO resourceDO = resourceService.getById(resourceId);
			if (resourceDO == null ) {
				continue;
			}
			roleResourceDO = new SystemRoleResourceDO();
			roleResourceDO.setRoleId(roleDO.getId());
			roleResourceDO.setResourceId(Long.parseLong(resourceId));
			roleResourceDO.setIsParent("0");
			roleResourceDO.setOrderNum(resourceDO.getOrderNum());
			roleResourceDO.setCreateDate(createDate);
			roleResourceDOs.add(roleResourceDO);
		}
		
		for(String resourceId:sonResourceId) {
			SystemResourceDO resourceDO = resourceService.getById(resourceId);
			if (resourceDO == null ) {
				continue;
			}
			roleResourceDO = new SystemRoleResourceDO();
			roleResourceDO.setRoleId(roleDO.getId());
			roleResourceDO.setOrderNum(resourceDO.getOrderNum());
			roleResourceDO.setResourceId(Long.parseLong(resourceId));
			roleResourceDO.setIsParent("1");
			roleResourceDO.setCreateDate(createDate);
			roleResourceDOs.add(roleResourceDO);
		}
		
		boolean result= roleResourceService.saveBatch(roleResourceDOs);
		JsonResult jsonResult = new JsonResult(result, result?"操作成功":"操作失败");
		return ResponseEntity.ok(jsonResult);
	}

	@Override
	public ResponseEntity<?> edit(HttpServletRequest request, SystemRoleDO t){
		Long roleId = RequestUtil.getValue(request, "id", 0L);
		
		SystemRoleResourceDO roleResourceDO = new SystemRoleResourceDO();
		roleResourceDO.setRoleId(roleId);
		
		roleResourceService.remove(new QueryWrapper<SystemRoleResourceDO>(roleResourceDO));
		
		String[] faResourceId = RequestUtil.getStringValue(request, "faResourceId").split(",");
		String[] sonResourceId = RequestUtil.getStringValue(request, "sonResourceId").split(",");

		Date createDate = new Date();
		List<SystemRoleResourceDO> roleResourceDOs = new ArrayList<>();
		for(String resourceId:faResourceId) {
			if(resourceId.equals("")) {
				continue;
			}
			SystemResourceDO resourceDO = resourceService.getById(resourceId);
			if (resourceDO == null ) {
				continue;
			}
			roleResourceDO = new SystemRoleResourceDO();
			roleResourceDO.setRoleId(roleId);
			roleResourceDO.setOrderNum(resourceDO.getOrderNum());
			roleResourceDO.setResourceId(Long.parseLong(resourceId));
			roleResourceDO.setIsParent("0");
			roleResourceDO.setCreateDate(createDate);
			roleResourceDOs.add(roleResourceDO);
		}
		
		for(String resourceId:sonResourceId) {
			if(resourceId.equals("")) {
				continue;
			}
			SystemResourceDO resourceDO = resourceService.getById(resourceId);
			if (resourceDO == null ) {
				continue;
			}
			roleResourceDO = new SystemRoleResourceDO();
			roleResourceDO.setRoleId(roleId);
			roleResourceDO.setOrderNum(resourceDO.getOrderNum());
			roleResourceDO.setResourceId(Long.parseLong(resourceId));
			roleResourceDO.setIsParent("1");
			roleResourceDO.setCreateDate(createDate);
			roleResourceDOs.add(roleResourceDO);
		}
		boolean result= roleResourceService.saveBatch(roleResourceDOs);
		JsonResult jsonResult = new JsonResult(result, result?"操作成功":"操作失败");
		return ResponseEntity.ok(jsonResult);
	}
}
