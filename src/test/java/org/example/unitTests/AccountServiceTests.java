package org.example.unitTests;

import org.example.exception.AccountNotFoundException;
import org.example.models.Account;
import org.example.repositories.AccountRepository;
import org.example.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private final int customerId = 101;
    private final int accountNumber = 1101;

    @BeforeEach
    void setUp() {
        Account account1 = new Account(1, "Account1", "Type1", customerId, 100.0, 100.0, false, false, "USD", "IBAN1", LocalDate.now(), LocalDate.now());
        Account account2 = new Account(2, "Account2", "Type2", customerId, 200.0, 200.0, false, false, "EUR", "IBAN2", LocalDate.now(), LocalDate.now());
        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountRepository.findByCustomerId(customerId)).thenReturn(accounts);
        when(accountRepository.findById(accountNumber)).thenReturn(Optional.of(account1));
        when(accountRepository.findById(999)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetAccountsByCustomerId() {
        List<Account> result = accountService.getAccountsByCustomerId(customerId);
        assertEquals(2, result.size());
        assertEquals("Account1", result.get(0).getAccountName());
        assertEquals("Account2", result.get(1).getAccountName());
    }

    @Test
    public void testGetAccountByAccountNumber() {
        Account result = accountService.getAccountByAccountNumber(accountNumber);
        assertEquals("Account1", result.getAccountName());
    }

    @Test
    public void testGetAccountByAccountNumber_AccountNotFound() {
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountByAccountNumber(999));
    }
}