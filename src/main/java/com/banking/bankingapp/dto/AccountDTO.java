package com.banking.bankingapp.dto;



import java.math.BigDecimal;

public class AccountDTO {

    private Long id;

    private String accountHolderName;

    private BigDecimal balance;

    public AccountDTO() {
    }

    public AccountDTO(String accountHolderName, BigDecimal balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
