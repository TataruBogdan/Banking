package com.intecbrussel.bankingdepositaccountsservice.deposit.dao;

import com.intecbrussel.bankingdepositaccountsservice.deposit.model.AccountDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<AccountDeposit, String> {

    List<AccountDeposit> findByIndividualId(Integer individualId);
}
