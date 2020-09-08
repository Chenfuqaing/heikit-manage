/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.core.shiro.ShiroConstants;

/**
 * 
 * <P> 
 *	登录   前端控制器
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:56:38
 */

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model){
		
        logger.debug("login post...");
		
		process(request, model);
        
		return "login.btl";
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.debug("login get...");
		process(request, model);
		return "login.btl";
	}
	
	private void process(HttpServletRequest request, Model model) {
		int notfound = RequestUtil.getIntValue(request, "nofound", 0);
		int logout = RequestUtil.getIntValue(request, "logout", 0);
		int blocked = RequestUtil.getIntValue(request, "blocked", 0);
		int unknown = RequestUtil.getIntValue(request, "unknown", 0);

		boolean hasError = false;
		String message = "";
		if (notfound == 1) {
			hasError = true;
			message = "用户名密码错误！";
		}
		if (logout == 1) {
			hasError = true;
			message = "你已成功退出系统";
		}
		if (blocked == 1) {
			hasError = true;
			message = "帐号被锁定了，无法进入系统";
		}
		if (unknown == 1) {
			hasError = true;
			message = "发生未知错误，无法进入系统";
		}
		Object errorMsg =  request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String errorMessage = "";
		if (errorMsg != null) {
			hasError = true;
			String errorClassName = "";
			
			if (errorMsg.equals(AuthenticationException.class.getName())) {
				errorClassName = AuthenticationException.class.getName();
			}
			
			if (errorMsg.equals(UnknownAccountException.class.getName()) ) {
				errorClassName = UnknownAccountException.class.getName();
			}
			
			
			if (errorClassName.equals(UnknownAccountException.class.getName()) || errorClassName.equals(AuthenticationException.class.getName())) {
				message = "用户名或密码错误，无法进入系统";
			} else {
				message = "未知错误，无法进入系统";
			}

		}
		model.addAttribute("hasError", hasError);
		model.addAttribute("message", message);
		model.addAttribute("errorMessage", errorMessage);

		// 如果用户直接到登录页面 先退出一下
		// 原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
		// 这样会导致登录一直死循环

		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			if (subject.isAuthenticated()) {
				subject.logout();
			}
		}

		Cookie[] cookies = request.getCookies();
		boolean isRememberMe = false;
		String rememberUser = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if ("SpringBootRememberMe".equals(name)) {
					String rememberMe = cookie.getValue();
					if ("true".equals(rememberMe.trim().toLowerCase())) {
						isRememberMe = true;
					}
				}

				if ("SpringBootRememberMe".equals(name)) {
					rememberUser = cookie.getValue();
				}
			}
		}
		if (isRememberMe) {
			model.addAttribute("username", rememberUser);
		}
	}
	
	@RequestMapping("logout")
	public String logout() {
		SecurityUtils.getSubject().getSession().removeAttribute(ShiroConstants.CURRENT_USER);
		return "login.btl";
	}
}
