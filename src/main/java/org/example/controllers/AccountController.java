package org.example.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.models.Account;
import org.example.models.AccountSummary;
import org.example.services.AccountService;
import org.example.services.RateLimitingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
@Api(tags = "Account Management API")
public class AccountController {

    private final AccountService accountService;
    private final RateLimitingService rateLimitingService;

    public AccountController(AccountService accountService, RateLimitingService rateLimitingService) {
        this.accountService = accountService;
        this.rateLimitingService = rateLimitingService;
    }

    @GetMapping
    @ApiOperation("Retrieve accounts by Customer ID")
    public ResponseEntity<List<AccountSummary>> getAccountsByCustomerId(@RequestParam int customerId, @RequestParam String apiKey) {
        if (rateLimitingService.isValidApiKey(apiKey)) {
            if (rateLimitingService.allowRequest(apiKey)) {
                List<Account> accounts = accountService.getAccountsByCustomerId(customerId);

                List<AccountSummary> accountSummaries = accounts.stream()
                        .map(account -> new AccountSummary(account.getAccountNumber(), account.getAccountName(), account.getAccountType(), account.getCurrency()))
                        .collect(Collectors.toList());

                return ResponseEntity.ok(accountSummaries);
            } else {
                return ResponseEntity.status(429).body(null); // Rate limit exceeded
            }
        } else {
            return ResponseEntity.status(401).body(null); // Invalid API key
        }
    }
    @GetMapping("/{accountNumber}")
    @ApiOperation("Retrieve account details by account number")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable int accountNumber, @RequestParam String apiKey) {
        if (rateLimitingService.isValidApiKey(apiKey)) {
            if (rateLimitingService.allowRequest(apiKey)) {
                Account account = accountService.getAccountByAccountNumber(accountNumber);
                if (account != null) {
                    return ResponseEntity.ok(account);
                } else {
                    return ResponseEntity.notFound().build(); // Account not found
                }
            } else {
                return ResponseEntity.status(429).body(null); // Rate limit exceeded
            }
        } else {
            return ResponseEntity.status(401).body(null); // Invalid API key
        }
    }
}
