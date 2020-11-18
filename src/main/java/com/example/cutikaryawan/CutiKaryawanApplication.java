package com.example.cutikaryawan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CutiKaryawanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CutiKaryawanApplication.class, args);
	}

}
