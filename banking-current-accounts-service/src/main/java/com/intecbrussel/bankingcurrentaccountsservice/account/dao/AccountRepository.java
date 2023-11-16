package com.intecbrussel.bankingcurrentaccountsservice.account.dao;

import com.intecbrussel.bankingcurrentaccountsservice.account.model.AccountCurrent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountCurrent, String> {
}
