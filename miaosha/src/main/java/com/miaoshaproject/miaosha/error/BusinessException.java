package com.miaoshaproject.miaosha.error;

/**
 * 包装器业务异常类实现
 * @author chenyujun
 *
 */
public class BusinessException extends Exception implements CommonError {
	
	private CommonError commonError;
	
	//直接接收EmBusinessError的传参用于构造业务异常
	public BusinessException(CommonError commonError) {
		// TODO Auto-generated constructor stub
		super();
		this.commonError = commonError;
	}
	
	//接收自定义errMsg的方式构造业务异常
	public BusinessException(CommonError commonError,String errMsg) {
		// TODO Auto-generated constructor stub
		super();
		this.commonError = commonError;
		this.commonError.setErrMsg(errMsg);
	}
	

	@Override
	public int getErrCode() {
		// TODO Auto-generated method stub
		return this.commonError.getErrCode();
	}

	@Override
	public String getErrMsg() {
		// TODO Auto-generated method stub
		return this.commonError.getErrMsg();
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
		// TODO Auto-generated method stub
		this.commonError.setErrMsg(errMsg);
		return this;
	}

}
