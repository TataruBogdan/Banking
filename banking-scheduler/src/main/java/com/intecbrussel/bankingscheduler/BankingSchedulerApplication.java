package com.intecbrussel.bankingscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankingSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSchedulerApplication.class, args);
	}

}
