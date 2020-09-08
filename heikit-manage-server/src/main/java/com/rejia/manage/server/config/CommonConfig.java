package com.rejia.manage.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:48:48
 */
@Configuration
@ComponentScan(basePackages={
		"com.rejia.manage.dbcore.service"
})
public class CommonConfig {

}
