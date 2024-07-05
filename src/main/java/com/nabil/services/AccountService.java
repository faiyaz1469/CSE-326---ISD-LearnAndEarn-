package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nabil.models.Account;
import com.nabil.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountRepository.findAll().forEach(accountList::add);
        return accountList;
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).get();
    }
    
    public boolean addAccount(Account account) {
        if(accountRepository.findByEmail(account.getEmail()) != null) return false;

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return true;
    }

    public void updateAccount(Account account) {
        Account t = accountRepository.findById(account.getId()).get();
        t = account;
        accountRepository.save(t);
    }

    public Account getAccountByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        return account;
    }
}
