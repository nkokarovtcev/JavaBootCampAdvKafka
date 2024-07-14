package com.nox.JavaBootCampAdvKafka;

import org.springframework.boot.SpringApplication;

public class TestJavaBootCampAdvKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.from(JavaBootCampAdvKafkaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
