/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.core.shiro.ShiroConstants;
import com.rejia.manage.dbcore.service.system.SystemResourceService;
import com.rejia.manage.dbcore.service.system.SystemRoleResourceService;
import com.rejia.manage.model.system.SystemResourceDO;
import com.rejia.manage.model.system.SystemRoleResourceDO;
import com.rejia.manage.model.user.UserDO;

/**
 * 
 * <P> 
 *	首页菜单栏  前段控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 10:07:14
 */
@Controller
@RequestMapping("manage")
public class IndexController {
	
	private @Resource SystemResourceService resourceService;
	private @Resource SystemRoleResourceService roleResourceService;
	
	/**
	 * 判断权限
	 */
	@RequestMapping("index")
	public String index(Model modle) {
		UserDO userDO = getUserFromSession();
		String[] roIdList = userDO.getRoleId().split(",");
		Set<Long> resourceIds = new HashSet<>();
//		Map<String, Object> columnMap =null;
		List<SystemResourceDO> resources = new ArrayList<>();
		SystemResourceDO resourceDO = null;
		
		for(String roId:roIdList) {
			QueryWrapper<SystemRoleResourceDO> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("role_id", roId);
			queryWrapper.eq("is_parent", "0").orderByDesc("order_num");
			List<SystemRoleResourceDO> resourceDOs = roleResourceService.list(queryWrapper);
//			columnMap = new HashMap<>();
//			columnMap.put("role_id", roId);
//			columnMap.put("is_parent", "0");
//			List<SystemRoleResourceDO> resourceDOs = roleResourceService.listByMap(columnMap);
			for(SystemRoleResourceDO resource:resourceDOs) {
				resourceIds.add(resource.getResourceId());
			}
		}
		TreeSet<Long> resourceIdss = new TreeSet<Long>(resourceIds);
		
		for(Long resoId:resourceIdss) {
			resourceDO = resourceService.getById(resoId);
			resources.add(resourceDO);
		}
//		if(resources.size()>=3) {
//			resourceDO = resources.get(2);
//			resources.remove(2);
//			resources.add(0,resourceDO);
//		}
		
		modle.addAttribute("resources", resources);
		return "manage/index.btl";
	}
	
	@RequestMapping("main")
	public String mainContainer(HttpServletRequest request, Model model) {
		UserDO userDO = getUserFromSession();
		model.addAttribute("userDO", userDO);
		return "main.btl";
	}
	
	@RequestMapping("menus")
	public ResponseEntity<?> getMenu(HttpServletRequest request){
		UserDO userDO = getUserFromSession();
		String[] roIdList = userDO.getRoleId().split(",");
		LinkedList<SystemResourceDO>  resourceDOs = new LinkedList<>();
		
		int moduleId = RequestUtil.getIntValue(request, "modularid", 0);
		
		Map<String, Object> columnMap = null;
		
		QueryWrapper<SystemResourceDO> entityWrapper = new QueryWrapper<>();
		entityWrapper.eq("parent_id", moduleId);
		entityWrapper.orderByDesc("order_num");
		
		List<SystemResourceDO> resources = resourceService.list(entityWrapper);
		for(SystemResourceDO resourceDO:resources) {
			for(String roId:roIdList) {
				columnMap = new HashMap<>();
				columnMap.put("resource_id", resourceDO.getId());
				columnMap.put("role_id", roId);
				Collection<SystemRoleResourceDO> roleResourceDOs = roleResourceService.listByMap(columnMap);
				if(roleResourceDOs.size()>0) {
					resourceDOs.add(resourceDO);
				}
			}
		}
		return ResponseEntity.ok(resourceDOs);
	}
	
	
	private UserDO getUserFromSession() {
		Object userObject = SecurityUtils.getSubject().getSession().getAttribute(ShiroConstants.CURRENT_USER);
		if (userObject instanceof UserDO) {
			return (UserDO) userObject;
		}
		return null ;
	}

}
