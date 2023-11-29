package com.intecbrussel.bankingtransactionsservice.dao;

import com.intecbrussel.bankingtransactionsservice.model.Transaction;
import com.intecbrussel.commonsservice.dto.types.AccountType;
import com.intecbrussel.commonsservice.dto.types.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Transaction findByFromIbanAndToIban(AccountType fromIban, AccountType toIban);

    @Query(value = "SELECT transaction_id FROM transaction where transaction_status in (?1)", nativeQuery = true)
    List<String> findTransactionByTransactionStatusList(List<String> statuses);

    @Modifying
    @Query(value = "INSERT INTO transaction (transaction_id, from_individual_id, from_iban, from_account_type, to_individual_id, to_iban, to_account_type, transaction_amount, transaction_timestamp, transaction_status) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
    void save(String transactionId, Integer fromIndividualId, String fromIban, AccountType fromAccountType, Integer toIndividualId, String toIban, AccountType toAccountType, Double transactionAmount, LocalDateTime transactionTime, TransactionStatus status);

}
