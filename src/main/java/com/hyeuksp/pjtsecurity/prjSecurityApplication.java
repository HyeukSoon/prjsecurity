package com.hyeuksp.pjtsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@PropertySource({"classpath:application.yml"})
@SpringBootApplication
public class prjSecurityApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(prjSecurityApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(prjSecurityApplication.class, args);
	}

}
