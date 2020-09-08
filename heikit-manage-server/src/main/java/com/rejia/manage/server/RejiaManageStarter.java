package com.rejia.manage.server;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * <P> 
 *		启动类
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:36:23
 */
@SpringBootApplication 
public class RejiaManageStarter extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.bannerMode(Mode.OFF);
		return super.configure(builder);
	}
	
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(RejiaManageStarter.class);
		springApplication.setBannerMode(Mode.OFF);
		springApplication.run(args);
	}

}