/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rejia.manage.model.product.ProjectDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:40:25
 */
public interface ProductService extends IService<ProjectDO> {

	IPage<ProjectDO> selectByParam(IPage<ProjectDO> page, String name, String createDateStart, String createDateEnd);
	
}
