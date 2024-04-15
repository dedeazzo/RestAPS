package org.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {
    @javax.persistence.Id
    @Column(name = "account_number")
    private int accountNumber;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "book_balance")
    private double bookBalance;
    @Column(name = "available_balance")
    private double availableBalance;
    @Column(name = "blocked")
    private boolean blocked;
    @Column(name = "closed")
    private boolean closed;
    @Column(name = "currency")
    private String currency;
    @Column(name = "iban")
    private String iban;
    @Column(name = "opening_date")
    private LocalDate openingDate;
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    public Account() {}

    public Account(int accountNumber, String accountName, String accountType, int customerId, double bookBalance, double availableBalance, boolean blocked, boolean closed, String currency, String iban, LocalDate openingDate, LocalDate lastModifiedDate) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.customerId = customerId;
        this.bookBalance = bookBalance;
        this.availableBalance = availableBalance;
        this.blocked = blocked;
        this.closed = closed;
        this.currency = currency;
        this.iban = iban;
        this.openingDate = openingDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public int getCustomerId() {
        return customerId;
    }

    public double getBookBalance() {
        return bookBalance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public boolean getClosed() {
        return closed;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIban() {
        return iban;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public LocalDate getLastModifiedDate() { return lastModifiedDate; }
}


