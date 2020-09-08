/**
 * Copyright 2017-2022(c) 北京海基特特富技术服务有限公司.All Rights Reserved.
 */
package com.rejia.manage.common.base;

import org.springframework.http.ResponseEntity;

import com.rejia.manage.common.dto.ResponseMessageDTO;
import com.rejia.manage.common.enums.ResponseMessageStatusEnum;


/**
 * 
 * <P> 
 *
 * <P>
 * @author 姓名：陈福强     <br>
 * 		         邮件：350096115@qq.com
 * 
 * @date 2020-8-3 9:36:57
 */
public class BaseCore {
	
    public ResponseEntity<?> createErrorMessage(String message){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
		responseMessageDTO.setStatus(ResponseMessageStatusEnum.ERROR);
        responseMessageDTO.setHasError(true);
        responseMessageDTO.setMessage(message);
        return ResponseEntity.ok(responseMessageDTO);
    }
    
    public ResponseEntity<?> createSuccessMessage(String message){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
		responseMessageDTO.setStatus(ResponseMessageStatusEnum.SUCCESS);
        responseMessageDTO.setMessage(message);
        return ResponseEntity.ok(responseMessageDTO);
    }

}
