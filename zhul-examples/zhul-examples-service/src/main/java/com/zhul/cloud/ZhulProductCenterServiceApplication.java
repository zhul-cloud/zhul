package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;

@EnableZhulService
public class ZhulProductCenterServiceApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "product-center");

		SpringApplication.run(ZhulProductCenterServiceApplication.class, args);
	}

}
