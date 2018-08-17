package com.thinkgem.elclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author zhaoqingjie
 */
@SpringBootApplication
@MapperScan({"com.thinkgem.elclient.dao"})
public class IotElApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(IotElApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IotElApplication.class);
	}

}
