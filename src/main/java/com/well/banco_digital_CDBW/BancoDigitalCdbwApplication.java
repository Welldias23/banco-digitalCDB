package com.well.banco_digital_CDBW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BancoDigitalCdbwApplication {
	public static void main(String[] args) {
		SpringApplication.run(BancoDigitalCdbwApplication.class, args);
	}
}
