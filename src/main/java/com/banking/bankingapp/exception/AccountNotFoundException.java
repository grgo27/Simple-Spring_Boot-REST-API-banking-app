package com.banking.bankingapp.exception;

import java.text.MessageFormat;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id){
        super(MessageFormat.format("Account with id: {0} doesnt exist", id));

    }
}
