/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 文件标题：CodeUtil<br>
 * 文件说明：code生成工具<br>
 * @author chenfuqiang 350096115@qq.com<br>
 * 创建时间2020年4月20日 上午9:44:48<br>
 */
public class CodeUtil {

	private static Map<Long, Integer> codeMap = Maps.newHashMap();
	
	/**
	 * 生成编号 格式 年(4位) 月(2位)  日 (2位) 时(2位) 分(2位) 秒(2位) 毫秒(3位) 三位顺序号(3位)
	 * @return 20位
	 */
	public static String generate(){
		return generate("");
	}
	
	/**
	 * 生成编号 格式 年(4位) 月(2位)  日 (2位) 时(2位) 分(2位) 秒(2位) 毫秒(3位) 三位顺序号(3位)
	 * @param prefix 前缀
	 * @return 前缀+20位
	 */
	public static String generate(String prefix){

		long timestamp = DateTimeUtils.currentTimeMillis();
		int sum = 1;
		if (codeMap.containsKey(timestamp)) {
			sum = codeMap.get(timestamp);
			sum = sum+1;
		}
		codeMap.put(timestamp, sum);
		
		DateTime dateTime = new DateTime(timestamp);
		
//		String random = StringUtils.leftPad(sum+"", 3, "0");
//		String result = dateTime.toString("yyyyMMddHHmmssSSS")+random;
		String result = dateTime.toString("yyyyMMddHHmm");

		clear(timestamp);
        result = prefix + result;
		return result;
	}
	
	/**
	 * 生成特定的code，生成编号 [前缀][天][id编号] A[01][00000001]
	 * @param id 数字
	 * @param prefix 前缀
	 * @param length 生成的最短长度
	 * @return 编号code
	 */
	public static String createCode(Long id, String prefix, Integer length){
		StringBuilder codeBuilder = new StringBuilder(prefix);
		codeBuilder.append(new DateTime().toString("dd"));
		codeBuilder.append(StringUtils.leftPad(id.toString(), length, "0"));
		return codeBuilder.toString();
	}
	
	/**
	 * 清空当前时间之前的内容
	 * @param timestamp
	 */
	private static void clear(Long timestamp){
		Set<Long> keySet = codeMap.keySet();
		List<Long> needRemoveList = Lists.newArrayList();
		for (Long time : keySet) {
			if (time<timestamp) {
				needRemoveList.add(time);
			}
		}
		
		if (needRemoveList.size()>0) {
			for (Long time : needRemoveList) {
				codeMap.remove(time);
			}
		}
	}
	
}
