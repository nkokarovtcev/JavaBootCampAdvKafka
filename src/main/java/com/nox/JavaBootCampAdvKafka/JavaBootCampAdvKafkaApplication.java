package com.nox.JavaBootCampAdvKafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaBootCampAdvKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaBootCampAdvKafkaApplication.class, args);
	}
}