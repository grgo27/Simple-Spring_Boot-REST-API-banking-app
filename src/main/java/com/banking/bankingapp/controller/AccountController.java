package com.banking.bankingapp.controller;

import com.banking.bankingapp.dto.AccountDTO;
import com.banking.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Endpoint for adding(saving) account
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.saveAccount(accountDTO), HttpStatus.CREATED); // saveAccount ce konvertat dto u account object, spremit u bazu i konvertat ponovo u dto
    }

    // Endpoint for getting single account
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }

    // Endpoint for depositing money
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> depositMoney(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request){ // ono sta unesemo u bodyu cemo pretvorit u map

        BigDecimal amount = request.get("amount"); // dohvatit amount value ppreko keya od Mapa, key je string i u postmanu cem stavit za ime amount
        AccountDTO accountDTO = accountService.deposit(id,amount); // argumente ubacit u accountService.deposit metodu koja taj deposit sprema u bazu i vraca dto objetk
        return ResponseEntity.ok(accountDTO);

    }

    // Endpoint for withdrawal money
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdrawMoney(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request){

        BigDecimal amount = request.get("amount"); // dohvatit amount value ppreko keya od Mapa, key je string i u postmanu cem stavit za ime amount
        AccountDTO accountDTO = accountService.withdraw(id,amount); // argumente ubacit u accountService.withdraw metodu koja taj deposit sprema u bazu i vraca dto objetk
        return ResponseEntity.ok(accountDTO);
    }

    // Endpoint for retrival of all accounts
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accountDTOS = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDTOS);
    }

    // Endpoint for deleting account
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted succesfully!");
    }
}
