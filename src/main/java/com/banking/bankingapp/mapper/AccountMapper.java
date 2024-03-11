package com.banking.bankingapp.mapper;

import com.banking.bankingapp.dto.AccountDTO;
import com.banking.bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDTO accountDTO){
        Account account = new Account();
        account.setAccountHolderName(accountDTO.getAccountHolderName());
        account.setBalance(accountDTO.getBalance());
        return account;
    }

    public  static  AccountDTO mapToAccountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountHolderName(account.getAccountHolderName());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }
}
