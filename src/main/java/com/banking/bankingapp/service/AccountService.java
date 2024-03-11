package com.banking.bankingapp.service;

import com.banking.bankingapp.dto.AccountDTO;
import com.banking.bankingapp.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    public AccountDTO saveAccount(AccountDTO accountDTO);

    public AccountDTO getAccountById(Long id);

    public AccountDTO deposit(Long id, BigDecimal amount);

    public AccountDTO withdraw(Long id, BigDecimal amount);

    public List<AccountDTO> getAllAccounts();

    public void deleteAccount(Long id);
}
