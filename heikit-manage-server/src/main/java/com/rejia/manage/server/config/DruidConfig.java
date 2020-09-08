package com.rejia.manage.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:48:54
 */
@Configuration
public class DruidConfig {
	 @Bean
	 public DruidDataSource dataSource(){
	    return DruidDataSourceBuilder.create().build();
	 }
}
