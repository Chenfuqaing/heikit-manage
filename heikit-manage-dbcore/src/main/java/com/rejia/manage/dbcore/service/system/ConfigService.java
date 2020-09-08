package com.rejia.manage.dbcore.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rejia.manage.model.system.ConfigDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 16:57:31
 */
public interface ConfigService extends IService<ConfigDO> {

	String selectByConfigKey(String configKey);
	
}
