package com.api.springrestfulapi.repository;

import com.api.springrestfulapi.model.Account;
import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.util.Response;
import org.apache.ibatis.annotations.*;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {


    @Select("select * from users")
    List<User> allUsers();
    List<User> findUserByUsername(String username);
    @Insert(("insert into users (name, gender, address)\n" +
            "values (#{user.name}, #{user.gender}, #{user.address})"))
    int createNewUser(@Param("user") User user);
    @Update("UPDATE users SET username=#{user.name},gender=#{user.gender},address=#{user.address} WHERE id=#{id}")
    int updateUser(@Param("user") User user,@Param("id") int id);

    @Select("SELECT * FROM users WHERE id=#{id}")
    User findUserById(int id);
    @Delete("DELETE FROM users WHERE id=#{id}")
    int removeUser(@Param("id") int id);


    @Results({

            @Result(column = "id",property = "accounts", many = @Many(select = "findAccountByID"))
    })
    @Select("select * from user_accounts")
    List<UserAccount> getAllUserAccounts();

    @Results({
            @Result(property = "accountType", column = "account_type", one = @One(select = "com.api.springrestfulapi.repository.AccountRepository.getAccountTypeByID"))
    })
    @Select(("select * from user_accounts inner join accounts a on a.id = user_accounts.account_id where user_id = #{id}"))
    List<Account> findAccountByID(int id);


}