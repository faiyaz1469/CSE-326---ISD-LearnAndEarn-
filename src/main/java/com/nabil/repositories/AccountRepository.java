package com.nabil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabil.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByEmail(String email);
}
