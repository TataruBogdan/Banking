package com.intecbrussel.bankingtransactionsservice.dao;

import com.intecbrussel.bankingtransactionsservice.model.Transaction;
import com.intecbrussel.commonsservice.dto.types.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Transaction findByFromIbanAndToIban(AccountType fromIban, AccountType toIban);

    @Query(value = "SELECT transaction_id FROM transaction where transaction_status in (:status)", nativeQuery = true)
    List<String> findTransactionByTransactionStatusList(List<String> statuses);
}
