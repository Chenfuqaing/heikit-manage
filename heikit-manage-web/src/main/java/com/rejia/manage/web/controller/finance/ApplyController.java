/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller.finance;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rejia.manage.common.util.CodeUtil;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.core.shiro.ShiroConstants;
import com.rejia.manage.dbcore.service.finance.ApplyService;
import com.rejia.manage.dbcore.service.system.ConfigService;
import com.rejia.manage.dbcore.service.user.UserService;
import com.rejia.manage.model.finance.ApplyDO;
import com.rejia.manage.model.user.UserDO;
import com.rejia.manage.web.controller.AbstractBaseController;

/**
 * <P> 
 *	报销前端控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 22:36:01
 */
@Controller
@RequestMapping("manage/finance/apply")
public class ApplyController extends AbstractBaseController<ApplyDO>{
	
	private final static String CONFIG_KEY = "apply_show";
	
	private @Resource ApplyService applyService;
	private @Resource UserService userService;
	private @Resource ConfigService configService;

	/**
	 * @param iService
	 */
	public ApplyController(ApplyService applyService) {
		super(applyService);
		this.templatePrefix = "manage/finance/apply/";
	}

	@Override
	public IPage<ApplyDO> getData(HttpServletRequest request, IPage<ApplyDO> pageRequest) {
		String applyUserName = RequestUtil.getStringValue(request, "apply_user_name");
		String costUser = RequestUtil.getStringValue(request, "cost_user");
		String applyDateStart = RequestUtil.getStringValue(request, "apply_date_start");
		String applyDateEnd = RequestUtil.getStringValue(request, "apply_date_end");
		UserDO userDO = getUserFromSession();
		if (userDO == null ) {
			return null;
		}
		String roleId = userDO.getRoleId();
		String value = configService.selectByConfigKey(CONFIG_KEY);
		if (StringUtils.isBlank(value)) {
			return null;
		}
		
		QueryWrapper<ApplyDO> wrapper = new QueryWrapper<>();
		
		if(StringUtils.isNotBlank(applyUserName)) {
			wrapper.like("apply_user_name", applyUserName);
		}
		if (!StringUtils.contains(value, roleId)) {
			wrapper.eq("apply_user_id", userDO.getId());
		}
		if(StringUtils.isNotBlank(costUser)) {
			wrapper.like("cost_user", costUser);
		}
		if(!StringUtils.isAnyEmpty(applyDateStart, applyDateEnd)) {
			wrapper.ge("apply_date", applyDateStart);
			wrapper.le("apply_date", applyDateEnd);
		}
		return applyService.page(pageRequest, wrapper);
	}

	@Override
	public void setRequest(HttpServletRequest request, Model model) {
		UserDO userDO = getUserFromSession();
		if (userDO == null ) {
			model.addAttribute("show_status", true);
			return ;
		}
		
		UserDO showUserDO = new UserDO();
		showUserDO.setId(userDO.getId());
		showUserDO.setRealName(userDO.getRealName());
		
		List<UserDO> userDOs = userService.selectByStatus();
		request.setAttribute("userDO", showUserDO);
		request.setAttribute("userDOs", userDOs);
		String value = configService.selectByConfigKey(CONFIG_KEY);
		if (StringUtils.isBlank(value)) {
			model.addAttribute("show_status", true);
			return ;
		}
		String roleId = userDO.getRoleId();
		if (StringUtils.contains(value, roleId)) {
			model.addAttribute("show_status", false);
			return ;
		}
		model.addAttribute("show_status", true);
		return ;
		
	}
	
	
	
	@Override
	public ApplyDO handleAdd(HttpServletRequest request, ApplyDO applyDO) {

		String applyUserId = applyDO.getApplyUserId();
		
		UserDO userDO = userService.getById(applyUserId);
		if (userDO != null ) {
			applyDO.setApplyUserName(userDO.getRealName());
		}
		
		
		Date createDate = new Date();
		applyDO.setCreateDate(createDate);
	    String code = CodeUtil.generate("BX");
	    applyDO.setCode(code);
		
		return applyDO;
	}
	
	@Override
	public ApplyDO handleEdit(HttpServletRequest request, ApplyDO applyDO) {
		String applyUserId = applyDO.getApplyUserId();
		UserDO userDO = userService.getById(applyUserId);
		if (userDO != null ) {
			applyDO.setApplyUserName(userDO.getRealName());
		}
		Date modifyDate = new Date();
		applyDO.setModifyDate(modifyDate);
		
		return applyDO;
	}
	
	@Override
	public String preAdd(HttpServletRequest request) {
		UserDO userDO = getUserFromSession();
		if (userDO == null ) {
			return null;
		}
		
		UserDO showUserDO = new UserDO();
		showUserDO.setId(userDO.getId());
		showUserDO.setRealName(userDO.getRealName());
		
		List<UserDO> userDOs = userService.selectByStatus();
		request.setAttribute("userDO", showUserDO);
		request.setAttribute("userDOs", userDOs);
		return templatePrefix + "pre_add.btl";
	}
	
	/**
	 * 获得操作员
	 * @return
	 */
	private UserDO getUserFromSession() {
		Object userObject = SecurityUtils.getSubject().getSession().getAttribute(ShiroConstants.CURRENT_USER);
		if (userObject instanceof UserDO) {
			return (UserDO) userObject;
		}
		return null ;
	}

}
