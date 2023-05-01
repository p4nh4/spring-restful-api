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
    // inject account service
    final private AccountService accountService;
    final private AutoAccountMapper autoAccountMapper;

    AccountController(AccountService accountService, AutoAccountMapper autoAccountMapper) {
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }

    @GetMapping("/all-accounts")
    public Response<List<AccountResponse>> getAllAccounts() {
        try {
            List<Account> allAccount = accountService.getAllAccount();
            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccount);
            return Response.<List<AccountResponse>>ok()
                    .setPayload(accountResponses)
                    .setMessage("successfully retrieved all account information ");

        } catch (Exception ex) {
            System.out.println("Something wrong : " + ex.getMessage());
            return Response.<List<AccountResponse>>exception().setMessage("Exception occurs! Failed to retrieved account information");
        }

    }


}
