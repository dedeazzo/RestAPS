package org.example.models;

public class AccountSummary {
    private int accountNumber;
    private String accountName;
    private String accountType;
    private String currency;

    public AccountSummary(int accountNumber, String accountName, String accountType, String currency) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.currency = currency;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCurrency() {
        return currency;
    }
}
