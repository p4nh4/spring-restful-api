package com.api.springrestfulapi.service;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.model.request.UserRequest;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByName(String name);

    User findUserByID (int id);
    int createNewUser (UserRequest user);
    int updateUser (UserRequest user, int id);
    int removeUser(int id);
    List<UserAccount> getAllUserAccounts();

}
