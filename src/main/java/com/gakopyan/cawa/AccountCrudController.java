package com.gakopyan.cawa;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AccountCrudController {
    @Autowired
    private TLSService tlsService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable("id") Long id) throws Exception {
        Account account = accountRepository.findOne(id);
        if (account == null) throw new Exception("Get account error. Can not find account with id:" + id);
        return account;
    }

    @PutMapping("/account/{id}")
    public Account createAccount(@PathVariable("id") Long id) throws Exception {
        if (accountRepository.exists(id)) throw new Exception("Create account error. Account exists, id:" + id);
        Account account = accountFromTlsService(id);
        accountRepository.save(account);
        return account;
    }

    @PostMapping("/account/{id}")
    public Account updateAccount(@PathVariable("id") Long id) throws Exception {
        if (!accountRepository.exists(id)) throw new Exception("Update account error. Can not find account with id:" + id);
        Account account = accountFromTlsService(id);
        accountRepository.save(account);
        return account;
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable("id") Long id) throws Exception {
        Account account = accountRepository.findOne(id);
        if (account == null) throw new Exception("Delete account error. Can not find account with id:" + id);
        accountRepository.delete(id);
    }

    @SuppressWarnings("unchecked")
    private Account accountFromTlsService(Long id) throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        String tlsRequest = jsonObject.toJSONString();
        String tlsResponse = tlsService.doRequest(tlsRequest);
        return Account.fromJsonString(tlsResponse);
    }
}
