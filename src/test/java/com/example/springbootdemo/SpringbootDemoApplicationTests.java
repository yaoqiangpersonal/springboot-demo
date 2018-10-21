package com.example.springbootdemo;


import com.example.springbootdemo.security.dao.PermissionDao;
import com.example.springbootdemo.security.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
		System.out.println(userDao.getByUserName("user"));
	}

}
