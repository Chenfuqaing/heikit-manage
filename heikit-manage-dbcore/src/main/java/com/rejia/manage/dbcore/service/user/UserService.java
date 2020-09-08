/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.service.user;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rejia.manage.model.user.UserDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:41:24
 */
public interface UserService extends IService<UserDO> {

	IPage<UserDO> pageParam(IPage<UserDO> pageRequest, String username, Integer status);

	UserDO selectByUsername(String username);

	List<UserDO> selectByStatus();

}
