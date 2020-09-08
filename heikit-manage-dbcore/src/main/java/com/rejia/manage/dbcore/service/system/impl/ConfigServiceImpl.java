package com.rejia.manage.dbcore.service.system.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rejia.manage.dbcore.dao.system.ConfigDao;
import com.rejia.manage.dbcore.service.system.ConfigService;
import com.rejia.manage.model.system.ConfigDO;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 16:58:29
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigDO> implements ConfigService {

	@Override
	public String selectByConfigKey(String configKey) {
		if (StringUtils.isBlank(configKey)) {
			return null;
		}
		QueryWrapper<ConfigDO> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("config_key", configKey);
		List<ConfigDO> list = list(queryWrapper);
		if (list == null || list.size() == 0) {
			return null;
		}
		
		return list.get(0).getValue();
	}
	
}
