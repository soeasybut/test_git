package com.miaoshaproject.miaosha.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.miaoshaproject.miaosha.error.BusinessException;
import com.miaoshaproject.miaosha.error.EmBusinessError;
import com.miaoshaproject.miaosha.response.CommonReturnType;
/**
 * 公共异常处理基类
 * @author chenyujun
 *
 */
public class BaseController {
	
	public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

	//定义exceptionhandler解决未被controller层吸收的exception
		@ExceptionHandler(Exception.class)
		@ResponseStatus(HttpStatus.OK)
		@ResponseBody
		public Object handlerException(HttpServletRequest request,Exception ex) {
			Map<String,Object> responseData = new HashMap();
			//判断是否抛出BusinessException异常
			if(ex instanceof BusinessException) {
				BusinessException businessException = (BusinessException)ex;
				responseData.put("errCode", businessException.getErrCode());
				responseData.put("errMsg", businessException.getErrMsg());
			}else {
				responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
				responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
			}
			return CommonReturnType.create(responseData,"fail");
			
		}
}
