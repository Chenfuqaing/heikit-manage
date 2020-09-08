/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.core.shiro;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.rejia.manage.dbcore.service.user.UserService;
import com.rejia.manage.model.user.UserDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:38:00
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

	private @Resource UserService systemUserService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		return authorizationInfo;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		usernamePasswordToken.setPassword(DigestUtils.md5Hex(String.valueOf(usernamePasswordToken.getPassword())).toCharArray());
		return super.supports(usernamePasswordToken);
	}
	
	@Override
	public void onLogout(PrincipalCollection principals) {
		SecurityUtils.getSubject().getSession().removeAttribute(ShiroConstants.CURRENT_USER);
		super.onLogout(principals);
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		if (authenticationToken != null) {
			UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
			String username = usernamePasswordToken.getUsername();
			UserDO entity = new UserDO();
			entity.setUsername(username);
//			QueryWrapper<UserDO> wrapper = new QueryWrapper<UserDO>(entity);
			UserDO systemUserDO =  systemUserService.selectByUsername(username);
			if (systemUserDO != null) {
				if (checkPassword(usernamePasswordToken, systemUserDO)) {
					SecurityUtils.getSubject().getSession().setAttribute(ShiroConstants.CURRENT_USER, systemUserDO);
					SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
							systemUserDO.getUsername(),
							systemUserDO.getPassword(),
							getName()
							);
					return simpleAuthenticationInfo;
				}else {
					return null;
				}
				
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	private boolean checkPassword(UsernamePasswordToken usernamePasswordToken, UserDO systemUserDO) {
		String inputPassword = String.valueOf(usernamePasswordToken.getPassword());
		return inputPassword.equals(systemUserDO.getPassword());
	}
	
}
 