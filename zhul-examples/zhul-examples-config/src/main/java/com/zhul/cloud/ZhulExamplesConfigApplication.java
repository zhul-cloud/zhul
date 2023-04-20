package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableZhulService
public class ZhulExamplesConfigApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ZhulExamplesConfigApplication.class, args);;
		String userName = applicationContext.getEnvironment().getProperty("user.name");
		String userAge = applicationContext.getEnvironment().getProperty("user.age");
		String userEnv = applicationContext.getEnvironment().getProperty("user.env");
		String userGroup = applicationContext.getEnvironment().getProperty("user.group");
		System.err.println("user name :"+userName+"; age: "+userAge + ";env:" + userEnv + ";group:" + userGroup);
	}

}
