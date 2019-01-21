package com.torfinn.demo1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;


import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;


@SpringBootApplication
@Configuration
@EnableSwagger2
public class Demo1Application {


	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.torfinn.demo1"))
				.paths(any()).build()
				.apiInfo(new ApiInfo("Torfinn Demo1 API's",
						"API's for demo-service", "2.0", null,
						new Contact("Torfinn Aas","https://www.torfinnaas.ml", ""),
						null, null, new ArrayList()));
	}


	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);
	}


	@PostConstruct
	private void init() {
	}

}




