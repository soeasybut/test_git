package com.miaoshaproject.miaosha.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaoshaproject.miaosha.controller.viewobject.UserVO;
import com.miaoshaproject.miaosha.error.BusinessException;
import com.miaoshaproject.miaosha.error.EmBusinessError;
import com.miaoshaproject.miaosha.response.CommonReturnType;
import com.miaoshaproject.miaosha.service.UserService;
import com.miaoshaproject.miaosha.service.model.UserModel;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	//用于获取otp短信接口
	@RequestMapping(value="/getotp",method= {RequestMethod.POST},consumes= {CONTENT_TYPE_FORMED})//作用是此请求必须映射到HTTP的POST请求才能生效
	@ResponseBody
	public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone) {
		//需要按照一定的规则生成OTP验证码
		Random random = new Random();
		int randomInt = random.nextInt(99999);//随机生成0-99999的数字
		randomInt += 10000;//此时随机数取值 10000-1099999
		String otpCode = String.valueOf(randomInt);
		
		//将OTP验证码同对应用户的手机关联,使用httpsession的方式绑定他的手机号与OTPCODE
		httpServletRequest.getSession().setAttribute(telphone,otpCode);
		
		//将OTP验证码通过短信通道发送给用户，省略
		System.out.println("telphone = " + telphone + " & otpCode = " + otpCode );
		return CommonReturnType.create(null);
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException {
		//调用service服务获取对应id的用户对象并返回给前端
		UserModel userModel = userService.getUserById(id);
		
		//若获取的对应用户信息不存在
		if(userModel == null) {
//			userModel.setEncrptPassword("123");//错误演示
			throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
		}
		//将核心领域模型用户对象转化为可供UI使用的viewobject
		UserVO userVO = convertFromModel(userModel);
		//返回通用对象
		return CommonReturnType.create(userVO);
	}
	
	private UserVO convertFromModel(UserModel userModel) {
		if(userModel == null) {
			return null;
		}
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(userModel, userVO);
		return userVO;
	}
	
	
}
