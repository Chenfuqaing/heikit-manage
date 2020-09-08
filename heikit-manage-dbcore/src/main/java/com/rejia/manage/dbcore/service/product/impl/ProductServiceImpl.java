/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.service.product.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rejia.manage.dbcore.dao.product.ProductDao;
import com.rejia.manage.dbcore.service.product.ProductService;
import com.rejia.manage.model.product.ProjectDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:40:16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProjectDO> implements ProductService {

	@Override
	public IPage<ProjectDO> selectByParam(IPage<ProjectDO> page, String name, String createDateStart, String createDateEnd) {
		QueryWrapper<ProjectDO> queryWrapper = new QueryWrapper<>();
		
		if(StringUtils.isNotEmpty(name)) {
			queryWrapper.like("name", name);
		}
		
		if(!StringUtils.isAnyEmpty(createDateStart, createDateEnd)) {
			queryWrapper.ge("create_date", createDateStart + " 00:00:00");
			queryWrapper.le("create_date", createDateEnd + " 23:59:59");
		}
		return page(page, queryWrapper);
	}
	
}
