package com.api.springrestfulapi.service.serviceImple;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.repository.UserRepository;
import com.api.springrestfulapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImple implements UserService {
    //inject repository
    UserRepository userRepository;
    UserServiceImple (UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> allUsers()
    {
        return userRepository.allUsers();
    }

    @Override
    public List<User> findUserByName(String name) {
        return null;
    }

    @Override
    public User findUserByID(int id) {
        return null;
    }

    @Override
    public int createNewUser(User user) {

        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(User user) {

//        return userRepository.updateUser(user);
        return 0;
    }

    @Override
    public int removeUser(int id) {
        return 0;
    }

    @Override
    public List<UserAccount> getAllUserAccounts()
    {
        return userRepository.getAllUserAccounts();
    }


}
