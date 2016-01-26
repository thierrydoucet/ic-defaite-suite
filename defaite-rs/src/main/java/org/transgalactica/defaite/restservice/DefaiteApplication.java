package org.transgalactica.defaite.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class DefaiteApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DefaiteApplication.class, args);
	}
}