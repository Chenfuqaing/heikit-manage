/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rejia.manage.dbcore.dao.system.SystemResourceDao;
import com.rejia.manage.dbcore.service.system.SystemResourceService;
import com.rejia.manage.model.system.SystemResourceDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:40:34
 */
@Service
public class SystemResourceServiceImpl extends ServiceImpl<SystemResourceDao, SystemResourceDO> implements SystemResourceService {
private @Resource SystemResourceDao resourceDao;
	
	@Override
	public List<SystemResourceDO> selectList(SystemResourceDO resource) {
		QueryWrapper<SystemResourceDO> wrapper = new QueryWrapper<SystemResourceDO>(resource);
		return resourceDao.selectList(wrapper);
	}

	@Override
	public List<SystemResourceDO> selectByType(int type) {
		SystemResourceDO resource = new SystemResourceDO();
		resource.setType(type);
		QueryWrapper<SystemResourceDO> wrapper = new QueryWrapper<SystemResourceDO>(resource);
		return resourceDao.selectList(wrapper);
	}

}
