package com.torfinn.demo1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;


@SpringBootApplication
public class Demo1Application {
	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);
	}


	@PostConstruct
	private void init() {
	}

}




