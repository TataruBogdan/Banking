package com.intecbrussel.bankingloansservice.loan.dao;

import com.intecbrussel.bankingloansservice.loan.model.AccountLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<AccountLoan, String> {

    List<AccountLoan> findByIndividualId(Integer individualId);
}
