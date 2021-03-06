package com.miaoshaproject.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miaoshaproject.miaosha.dao.UserDOMapper;
import com.miaoshaproject.miaosha.dataobject.UserDO;

/**
 * Hello world!
 *
 */
//声明为主启动类并进行托管,将com.miaoshaproject根目录下的包依次进行往下扫描，能够通过注解的方式自动发现在对应的一些注解
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject.miaosha"})
@RestController
@MapperScan("com.miaoshaproject.miaosha.dao")
public class App 
{
	@Autowired
	private UserDOMapper userDOMapper;
	
	@RequestMapping("/")
	public String home() {
		UserDO userDO = userDOMapper.selectByPrimaryKey(1);
		if(userDO==null) {
			return "用户对象不存在!";
		}else {
			return userDO.getName();
		}
	}
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
}
