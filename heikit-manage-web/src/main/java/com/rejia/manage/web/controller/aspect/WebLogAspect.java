/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.web.controller.aspect;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.rejia.manage.core.shiro.ShiroConstants;
import com.rejia.manage.dbcore.service.system.OperationLogService;
import com.rejia.manage.model.system.OperationLogDO;
import com.rejia.manage.model.user.UserDO;

 
/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 10:05:12
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class WebLogAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);
	private @Resource OperationLogService operationLogService;
	private String url = "";
	private String httpMethod = "";
	private String ip = "";
	private String classMethod = "";
	private String args = "";
	//@Pointcut("execution(* update*(..)) or execution(* insert*(..)) or execution(* delete*(..))")
	@Pointcut("execution(* com.rejia.manage.web.controller..*.add*(..)) "
			+ "|| execution(* com.rejia.manage.web.controller..*.edit*(..))"
			+ "|| execution(* com.rejia.manage.web.controller..*.list*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		//LOGGER.info("URL : " + request.getRequestURL().toString());
		//LOGGER.info("HTTP_METHOD : " + request.getMethod());
		//LOGGER.info("IP : " + request.getRemoteAddr());
		//LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
		//		+ joinPoint.getSignature().getName());
		//LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		
		url = request.getRequestURL().toString();
		httpMethod = request.getMethod();
		ip = request.getRemoteAddr();
		classMethod = joinPoint.getSignature().getDeclaringTypeName() + "."+ joinPoint.getSignature().getName();
		args = Arrays.toString(joinPoint.getArgs());
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		//LOGGER.info("RESPONSE : " + JSONObject.toJSONString(ret));
		UserDO systemUserDO = (UserDO) SecurityUtils.getSubject().getSession().getAttribute(ShiroConstants.CURRENT_USER);
		
		Integer userId = 0;
		String userName = null;
		if (systemUserDO != null) {
			userId = systemUserDO.getId();
			userName = systemUserDO.getUsername();
		}
		
		OperationLogDO systemLogDO = new OperationLogDO();
		systemLogDO.setUserId(userId);
		systemLogDO.setUserName(userName);
		systemLogDO.setUrl(url);
		systemLogDO.setHttpMethod(httpMethod);
		systemLogDO.setClassMethod(classMethod);
		systemLogDO.setIp(ip);
		systemLogDO.setArgs(args);
		systemLogDO.setResponse(JSONObject.toJSONString(ret));
		systemLogDO.setCreateDate(new Date());
		try {
			operationLogService.save(systemLogDO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

}