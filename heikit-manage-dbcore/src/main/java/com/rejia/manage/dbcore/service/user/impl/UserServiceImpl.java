/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rejia.manage.dbcore.dao.user.UserDao;
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
 * @date 2020-8-3 9:41:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {

	@Override
	public IPage<UserDO> pageParam(IPage<UserDO> pageRequest, String username, Integer status) {
		QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(username)) {
			queryWrapper.eq("user_name", username);
		}
		queryWrapper.eq("status", status);
		
		return page(pageRequest, queryWrapper);
	}

	@Override
	public UserDO selectByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", username);
		List<UserDO> list = list(queryWrapper);
		if (list == null || list.size() == 0) {
			return null;
		}
		
		return list.get(0);
		
	}

	@Override
	public List<UserDO> selectByStatus() {
		QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status", 0);
		List<UserDO> list = list(queryWrapper);
		if (list == null || list.size() == 0) {
			return null;
		}
		List<UserDO> showDos = new ArrayList<>();
		for (UserDO userDO : list) {
			UserDO showDo = new UserDO();
			showDo.setId(userDO.getId());
			showDo.setUsername(userDO.getUsername());
			showDo.setRealName(userDO.getRealName());
			showDos.add(showDo);
		}
		return showDos;
	}

}
