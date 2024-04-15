package org.example.services;

import org.example.exception.AccountNotFoundException;
import org.example.exception.CustomerNotFoundException;
import org.example.models.Account;
import org.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> customerAccounts = accountRepository.findByCustomerId(customerId);
        if (customerAccounts.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customerAccounts;
    }

    public Account getAccountByAccountNumber(int accountNumber) {
        Optional<Account> accountOptional = accountRepository.findById(accountNumber);
        return accountOptional.orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
}