package com.api.springrestfulapi.service.serviceImple;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.model.request.UserRequest;
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

        return userRepository.findUserById(id);
    }

    @Override
    public int createNewUser(UserRequest user) {

        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(UserRequest user, int id) {

        return userRepository.updateUser(user,id);

    }

    @Override
    public int removeUser(int id) {
        return userRepository.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccounts()
    {
        return userRepository.getAllUserAccounts();
    }


}
