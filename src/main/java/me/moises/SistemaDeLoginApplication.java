package me.moises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SistemaDeLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeLoginApplication.class, args);
	}

}
