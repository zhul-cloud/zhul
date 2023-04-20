package com.zhul.cloud;

import com.zhul.cloud.framework.service.EnableZhulService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableZhulService
public class ZhulExamplesCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhulExamplesCacheApplication.class, args);
	}

}
