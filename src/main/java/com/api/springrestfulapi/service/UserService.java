package com.api.springrestfulapi.service;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByName(String name);

    User findUserByID (int id);
    int createNewUser (User user);
    int updateUser (User user);
    int removeUser(int id);
    List<UserAccount> getAllUserAccounts();

}
