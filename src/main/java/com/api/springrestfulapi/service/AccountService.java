package com.api.springrestfulapi.service;

import com.api.springrestfulapi.model.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccount();
    int createAccount(Account account);
    int updateAccount(Account account);
    Account findAccountByID(int id);
}
