/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.util;

import com.rejia.manage.common.dto.JsonResult;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-7 10:00:42
 */
public class JsonResultUtil {
	
	public static JsonResult getResult(boolean result) {
		JsonResult jsonResult = new JsonResult();
		if(!result) {
			jsonResult.setMsg("操作成功");
		}else {
			jsonResult.setMsg("操作失败");
		}
		jsonResult.setResult(result);
		return jsonResult;
	}

}
