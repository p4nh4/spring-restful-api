package com.api.springrestfulapi.repository;

import com.api.springrestfulapi.model.Account;
import com.api.springrestfulapi.model.AccountType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AccountRepository {
    @Result(column = "account_type",property ="accountType",one = @One(select = "getAccountTypeByID"))
    @Select("select *from accounts")
    List<Account> getAllAccount();
//    @Result(column = "name",property = "accountName")
    @Select("SELECT * FROM account_types WHERE id=#{accountType}")
    AccountType getAccountTypeByID(int accountType);


    int createAccount(Account account);
    int updateAccount (Account account);
    Account findAccountByID(int id);
}
