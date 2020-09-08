/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.dbcore.dao.common;

import java.util.List;
import java.util.Map;

public interface CustomMapperDao {

	/**
	 * 根据sql获取结果
	 * @param sql
	 * @return 结果集
	 */
	List<Map<String, Object>> selectBySql(String sql);
	/**
	 * 根据sql计算count
	 * @param countSql 计算sql
	 * @return 总条数
	 */
	long selectCountBySql(String countSql);
	/**
	 * 根据sql计算sum值
	 * @param sumSql 总和
	 * @return 综合数
	 */
	double selectSumSql(String sumSql);
	
}
