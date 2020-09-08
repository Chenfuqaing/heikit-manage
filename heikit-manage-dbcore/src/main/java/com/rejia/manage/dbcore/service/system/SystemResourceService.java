/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.service.system;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rejia.manage.model.system.SystemResourceDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:40:50
 */
public interface SystemResourceService extends IService<SystemResourceDO> {

	/**
	 * @param resource
	 * @return
	 */
	List<SystemResourceDO> selectList(SystemResourceDO resource);

	/**
	 * @param i
	 * @return
	 */
	List<SystemResourceDO> selectByType(int type);

}
