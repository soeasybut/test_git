package com.miaoshaproject.miaosha.service.model;

public class UserModel {

	private Integer id;//主键
	private String name;//名字
	private Byte gender;//1代表男性，2代表女性
	private Integer age;//年龄
	private String telphone;//电话
	private String registerMode;//
	private String thirdPartyId;//
	private String encrptPassword;//加密密码
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getRegisterMode() {
		return registerMode;
	}
	public void setRegisterMode(String registerMode) {
		this.registerMode = registerMode;
	}
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	public String getEncrptPassword() {
		return encrptPassword;
	}
	public void setEncrptPassword(String encrptPassword) {
		this.encrptPassword = encrptPassword;
	}
	
	
}
