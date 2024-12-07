package com.acc.validate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info (title="Student Swagger-UI",version = "v 3.0", description = "case study"))
public class BeanValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeanValidationApplication.class, args);
	}

}
