package br.com.vitta.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "br.com.vitta" })
public class TerritoriesApp {

	public static void main(String[] args) {
		SpringApplication.run(TerritoriesApp.class, args);
	}
}
