package com.rejia.manage.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * <p>mybatis-plus 配置文件</p>
 *
 */
@Configuration
@MapperScan(basePackages={
		"com.rejia.manage.dbcore.dao"
})
public class MybatisPlusConfigurator {
	
	@Bean
	public PaginationInterceptor paginationInterceptor(){
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
	    paginationInterceptor.setDbType(DbType.MYSQL);//配置数据库类型
		return paginationInterceptor;
	}
}
