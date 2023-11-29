package com.intecbrussel.bankingtransactionsservice;

import com.intecbrussel.bankingtransactionsservice.dao.TransactionRepository;
import com.intecbrussel.bankingtransactionsservice.model.Transaction;
import com.intecbrussel.commonsservice.dto.types.AccountType;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@SpringBootApplication
public class BankingTransactionsServiceApplication{

	//private Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	private TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankingTransactionsServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
////		        logger.info("Insert transaction -> {}", transactionRepository.save(
////                new Transaction(321, 1, "ABC123", AccountType.CURRENT, "BCD123", AccountType.CURRENT, 1000,
////                        new Date(), TransactionStatus.APPROVED)
////        ));
//	}
}
