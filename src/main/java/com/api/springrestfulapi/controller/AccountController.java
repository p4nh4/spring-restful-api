package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.mapper.AutoAccountMapper;
import com.api.springrestfulapi.model.Account;
import com.api.springrestfulapi.model.response.AccountResponse;
import com.api.springrestfulapi.service.AccountService;
import com.api.springrestfulapi.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    //inject service
    private final AccountService accountService;
    private final AutoAccountMapper autoAccountMapper;
    public AccountController(AccountService accountService, AutoAccountMapper autoAccountMapper) {
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }
    @GetMapping("/allaccounts")
    public Response<List<AccountResponse>> getAllAccounts(){
        try {
            List<Account> allAccounts = accountService.getAllAccount();
            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccounts);
            return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("retrieved...!");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return Response.<List<AccountResponse>>exception().setMessage("failed LOL...!");
        }
    }
}
