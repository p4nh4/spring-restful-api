package com.api.springrestfulapi.service;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.model.request.UserRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
//    List<User> allUsers();
    PageInfo<User> allUsers( int page, int size, String filterName);
    User findUserByID (int id);
    int createNewUser (UserRequest user);
    int updateUser (UserRequest user, int id);
    int removeUser(int id);
    List<UserAccount> getAllUserAccounts();
}
