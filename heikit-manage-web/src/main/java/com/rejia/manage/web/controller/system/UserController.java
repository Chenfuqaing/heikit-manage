/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller.system;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rejia.manage.common.dto.JsonResult;
import com.rejia.manage.common.util.JsonResultUtil;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.dbcore.service.system.SystemRoleService;
import com.rejia.manage.dbcore.service.user.UserService;
import com.rejia.manage.model.system.SystemRoleDO;
import com.rejia.manage.model.user.UserDO;
import com.rejia.manage.web.controller.AbstractBaseController;

/**
 * 
 * <P> 
 *	 系统用户 前端控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-6 11:03:06
 */
@Controller
@RequestMapping("manage/system/user")
public class UserController extends AbstractBaseController<UserDO>{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private @Resource UserService userService;
	private @Resource SystemRoleService roleService;
	
	@Autowired
	public UserController(UserService userService) {
		super(userService);
		this.templatePrefix = "manage/system/user/";
	}

	@Override
	public IPage<UserDO> getData(HttpServletRequest request, IPage<UserDO> page) {
		String username = RequestUtil.getStringValue(request, "username");
		String realname = RequestUtil.getStringValue(request, "realname");
		
		QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(username)) {
			wrapper.eq("username", username);
		}
		if(StringUtils.isNotEmpty(realname)) {
			wrapper.eq("realname", realname);
		}
		return userService.page(page, wrapper);
	}
	
	@RequestMapping("pre_editPassWord")
	public String editPassWord(HttpServletRequest request, Model model) {
		String username = RequestUtil.getStringValue(request, "username");
		UserDO userDO = userService.selectByUsername(username);
		model.addAttribute("userDO", userDO);
		return this.templatePrefix+"editPassWord.btl";
	}
	
	@RequestMapping("add_page")
	public String add(HttpServletRequest request, Model model) {
		List<SystemRoleDO> roleDOs = roleService.list(null);
		model.addAttribute("roleDOs", roleDOs);
		return this.templatePrefix+"pre_add.btl";
	}

	@Override
	public void setRequest(HttpServletRequest request, Model model) {
		List<SystemRoleDO> roleDOs = roleService.list(null);
		
		Long id = RequestUtil.getValue(request, "id", 0L);
		UserDO userDO = userService.getById(id);
		String []roleId = userDO.getRoleId().split(",");
		
		List<Long> roleIds = new ArrayList<>();
		for(String roleid:roleId) {
			roleIds.add(Long.parseLong(roleid));
		}
		
		model.addAttribute("roleIds", roleIds);
		model.addAttribute("userDO", userDO);
		model.addAttribute("roleDOs",roleDOs);
	}

	@RequestMapping("editPsw")
	@ResponseBody
	public ResponseEntity<?> editPsw(HttpServletRequest request, UserDO userDO){
		UserDO userDO2 = userService.getById(userDO.getId());
		String oldPassword = DigestUtils.md5Hex(userDO.getOldPassword());
		String password = userDO2.getPassword();
		if (StringUtils.isAnyEmpty(oldPassword,password) ) {
			logger.error("输入的密码为空");
			JsonResult jsonResult = JsonResultUtil.getResult(false);
			return ResponseEntity.ok(jsonResult);
		}
		if (!password.equals(oldPassword)) {
			logger.error("旧密码输入不正确");
			JsonResult jsonResult = JsonResultUtil.getResult(false);
			return ResponseEntity.ok(jsonResult);
		}
		userDO.setPassword(DigestUtils.md5Hex(userDO.getPassword()));
		
		Date modifyDate = new Date();
		
		userDO.setModifyDate(modifyDate);
		boolean  result = userService.updateById(userDO);
		JsonResult jsonResult = new JsonResult(result, result ? "操作成功" : "操作失败");
		return ResponseEntity.ok(jsonResult);
	}
	
	@Override
	public ResponseEntity<?> add(HttpServletRequest request, UserDO userDO){
		Date createDate = new Date();
		UserDO user = new UserDO();
		user.setUsername(userDO.getUsername());
		QueryWrapper<UserDO> wrapper = new QueryWrapper<UserDO>(user);
		user = userService.getOne(wrapper);
		if(user!=null) {
			logger.error("用户已存在,不可再次添加");
			JsonResult jsonResult = JsonResultUtil.getResult(false);
			return ResponseEntity.ok(jsonResult);
		}
		userDO.setCreateDate(createDate);
		userDO.setPassword(DigestUtils.md5Hex(userDO.getPassword()));
		boolean result = userService.save(userDO);
		JsonResult jsonResult = new JsonResult(result, result?"操作成功":"操作失败");
		return ResponseEntity.ok(jsonResult);
	}

	@Override
	public UserDO handleEdit(HttpServletRequest request, UserDO userDO) {
		Date modifyDate = new Date();
		
		userDO.setModifyDate(modifyDate);
		return userDO;
	}
	
}
