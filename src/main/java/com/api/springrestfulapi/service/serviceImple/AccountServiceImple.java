package com.api.springrestfulapi.service.serviceImple;

import com.api.springrestfulapi.model.Account;
import com.api.springrestfulapi.repository.AccountRepository;
import com.api.springrestfulapi.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImple implements AccountService {
    //inject repo
    final private AccountRepository accountRepository;

    public AccountServiceImple(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    @Override
    public int createAccount(Account account) {
        return 0;
    }

    @Override
    public int updateAccount(Account account) {
        return 0;
    }

    @Override
    public Account findAccountByID(int id) {
        return accountRepository.findAccountByID(id);
    }
}
