package com.zhul.cloud.examples;

import com.zhul.cloud.examples.entity.UserDO;
import com.zhul.cloud.examples.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class ZhulExamplesDatabaseApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		UserDO user = userMapper.selectById(1);
		System.out.println(user);
	}

}
