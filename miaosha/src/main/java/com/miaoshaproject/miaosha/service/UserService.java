package com.miaoshaproject.miaosha.service;

import com.miaoshaproject.miaosha.service.model.UserModel;

public interface UserService {

	//通过用户ID获取用户对象
	UserModel getUserById(Integer id);
}
