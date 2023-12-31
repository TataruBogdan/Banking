package com.intecbrussel.bankingcurrentaccountsservice.account.dao;

import com.intecbrussel.bankingcurrentaccountsservice.account.model.AccountCurrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountCurrent, String> {

    List<AccountCurrent> findByIndividualId(int individualId);
}
