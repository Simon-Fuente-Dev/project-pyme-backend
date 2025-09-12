package com.proyecto.proyecto_pyme_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
public class ProyectoPymeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPymeBackendApplication.class, args);
	}

}
