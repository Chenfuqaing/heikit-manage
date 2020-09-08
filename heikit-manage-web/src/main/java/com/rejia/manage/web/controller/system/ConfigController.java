package com.rejia.manage.web.controller.system;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rejia.manage.common.util.RequestUtil;
import com.rejia.manage.dbcore.service.system.ConfigService;
import com.rejia.manage.model.system.ConfigDO;
import com.rejia.manage.web.controller.AbstractBaseController;

/**
 * <p>
 *  全局配置 前端控制器
 * </p>
 *
 * @author YiZhi
 * @since 2018-08-07
 */
@Controller
@RequestMapping("manage/system/config")
public class ConfigController extends AbstractBaseController<ConfigDO>{
	private @Resource ConfigService configService;
	
	@Autowired
	public ConfigController(ConfigService configService) {
		super(configService);
		this.templatePrefix = "manage/system/config/";
	}
	
	@Override
	public IPage<ConfigDO> getData(HttpServletRequest request,IPage<ConfigDO> page) {
		return configService.page(page, null);

	}

	@Override
	public void setRequest(HttpServletRequest request, Model model) {
		ConfigDO configDO = null;
		
		Long id = RequestUtil.getValue(request, "id", 0L);
		if (id>0) {
			configDO = configService.getById(id);
		}
		
		model.addAttribute("configDO", configDO);
	}

	@Override
	public ConfigDO handleAdd(HttpServletRequest request, ConfigDO configDO) {
		Date createDate = new Date();
		configDO.setCreateDate(createDate);
		return configDO;
	}

	@Override
	public ConfigDO handleEdit(HttpServletRequest request, ConfigDO configDO) {
		Date modifyDate = new Date();
		configDO.setModifyDate(modifyDate);
		return configDO;
	}
}
