package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.service.UserService;
import com.api.springrestfulapi.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;
    UserRestController(UserService userService)
    {
        this.userService = userService;
    }
    @GetMapping("/alluser")
    List<User> getAllUser()
    {
        return userService.allUsers();
    }
    @GetMapping("/users/{id}")
    public User findUserByID(@PathVariable int id)
    {
        return userService.findUserByID(id);
    }
    @PostMapping("/newuser")
    public String createUser(@RequestBody User user)
    {
        try{
            int rowE = userService.createNewUser(user);
           if(rowE>0)
           {
               return "success!";
           }else {
               return "not success!";
           }

        }catch (Exception e)
        {
            return e.getMessage();
        }
    }
    @GetMapping("/user-accounts")
    public Response<List<UserAccount>> getAllUserAccounts(){
        try{
            List<UserAccount> data = userService.getAllUserAccounts();
            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("retrieved all user accounts !");
        }catch (Exception ex){
            return Response.<List<UserAccount>>exception().setMessage(ex)
                    .setSuccess(false);
        }
    }
    @GetMapping("/update/{id}")
    Response<User> updateUser(@RequestBody User user,@PathVariable("id") Integer id){
        try {
            int result= userService.updateUser(user);
            if(result>0) {
                return Response.<User>updateSuccess().setPayload(user).setMessage("update successfully.");
            }else {
                return Response.<User>ok().setMessage("user with "+id+" not found");
            }
        }catch (Exception e){
            System.out.println("error:"+e);
            return Response.<User>exception().setMessage("Update Fail.").setSuccess(false);
        }
    }
    @DeleteMapping("/remove/{id}")
    Response<User> removeUser(@PathVariable("id")int id){
        try {
            int result= userService.removeUser(id);
            if(result>0) {
                return Response.<User>deleteSuccess().setMessage("One Row Is Delete.");
            }else {
                return Response.<User>ok().setMessage("user with "+id+" not found");
            }
        }catch (Exception e){
            System.out.println("error:"+e);
            return Response.<User>exception().setMessage("Remove Fail.").setSuccess(false);
        }

    }
}
