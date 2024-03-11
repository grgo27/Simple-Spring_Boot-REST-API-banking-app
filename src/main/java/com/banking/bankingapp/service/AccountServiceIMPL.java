package com.banking.bankingapp.service;

import com.banking.bankingapp.dto.AccountDTO;
import com.banking.bankingapp.entity.Account;
import com.banking.bankingapp.exception.AccountNotFoundException;
import com.banking.bankingapp.mapper.AccountMapper;
import com.banking.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceIMPL implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceIMPL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO); // convert accountDTO to account
        Account savedAccount = accountRepository.save(account); // save account object to db
        return AccountMapper.mapToAccountDTO(savedAccount); // return account dto object
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id)); // iz baze dohvacan account objekt
        return AccountMapper.mapToAccountDTO(account); // vracan dto objekt
    }

    @Override
    public AccountDTO deposit(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        BigDecimal total = account.getBalance().add(amount); // zbrojit trenutni balance sa amounton koji zelimo dodat
        account.setBalance(total); // setat mu zbrojeni novi balance
        Account savedAccount = accountRepository.save(account); // spremit updateani account
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(Long id, BigDecimal amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));

        if (account.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Not enough funds");
        }

        BigDecimal total = account.getBalance().subtract(amount); // oduzet trenutni balance i amount
        account.setBalance(total); // setat mu oduzeti novi balance
        Account savedAccount = accountRepository.save(account); // spremit updateani account
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountsDTO = accounts.stream().map(AccountMapper::mapToAccountDTO).collect(Collectors.toList());
        return accountsDTO;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        accountRepository.deleteById(id);
    }
}
