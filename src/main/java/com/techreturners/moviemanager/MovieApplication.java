package com.techreturners.moviemanager;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	@Bean
	public OpenAPI movieOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Movie API")
						.description("Movie application"));
	}
}
