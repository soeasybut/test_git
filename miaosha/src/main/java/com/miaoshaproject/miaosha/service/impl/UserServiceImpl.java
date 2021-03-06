package com.miaoshaproject.miaosha.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miaoshaproject.miaosha.dao.UserDOMapper;
import com.miaoshaproject.miaosha.dao.UserPasswordDOMapper;
import com.miaoshaproject.miaosha.dataobject.UserDO;
import com.miaoshaproject.miaosha.dataobject.UserPasswordDO;
import com.miaoshaproject.miaosha.service.UserService;
import com.miaoshaproject.miaosha.service.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDOMapper userDOMapper;
	@Autowired
	private UserPasswordDOMapper userPasswordDOMapper;
	
	@Override
	public UserModel getUserById(Integer id) {
		// 调用userDOMapper获取到对应的用户dataobjecct
		UserDO userDO = userDOMapper.selectByPrimaryKey(id);
		if(userDO == null) {
			return null;
		}
		//通过用户ID获取对应用户的加密密码信息
		UserPasswordDO userPasswordDO =  userPasswordDOMapper.selectByUserId(userDO.getId());
		return convertFromDataObject(userDO, userPasswordDO);
	}

	private UserModel convertFromDataObject(UserDO userDO ,UserPasswordDO userPasswordDO) {
		if(userDO == null) {
			return null;
		}
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDO, userModel);
		if(userPasswordDO !=null) {
			userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
		}
		return userModel;
	}
	
}
