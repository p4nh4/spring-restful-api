package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.model.request.UserRequest;
import com.api.springrestfulapi.service.UserService;
import com.api.springrestfulapi.util.Response;
import jakarta.validation.Valid;
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
    public Response<List<User>> getAllUser()
    {
        try {
            List<User> response = userService.allUsers();
            return Response.<List<User>>ok().setPayload(response).setMessage("successfully retrieved!");

        }catch (Exception e)
        {
            return Response.<List<User>>exception().setMessage("retrieved failed LMAO");
        }
    }
    @GetMapping("/{id}")
    public Response<User> findUserByID(@PathVariable int id)
    {
        try {
            User response = userService.findUserByID(id);
            if(response!=null)
            {
                return Response.<User>ok().setPayload(response).setSuccess(true).setMessage(id+"is here...!");
            }
            else
            {
                return Response.<User>notFound().setMessage(id+" not existed!").setSuccess(false);
            }

        }catch (Exception e)
        {
            return Response.<User>exception().setMessage(id+" not existed!");
        }

    }
    @PostMapping("/newuser")
    public Response<User> createUser(@Valid @RequestBody UserRequest request)
    {
        try{

            int rowE = userService.createNewUser(request);
           if(rowE>0)
           {
               User response = new User().setName(request.getName())
                       .setGender(request.getGender())
                       .setAddress(request.getAddress())
                       .setId(rowE)
                       ;
               return Response.<User>createSuccess().setPayload(response).setMessage("created!").setSuccess(true);
           }else {
               return Response.<User>badRequest().setMessage("failed to create!");
           }
        }catch (Exception e)
        {
            return Response.<User>exception().setMessage("failed to create!")
                    .setSuccess(false);
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
    @PutMapping ("/{id}")
    Response<User> updateUserByID(@RequestBody UserRequest request,@PathVariable("id") int id){
        try {
            int result= userService.updateUser(request,id);
            if(result>0) {
                User response = new User().setId(id).setName(request.getName()).setGender(request.getGender()).setAddress(request.getAddress());
                return Response.<User>updateSuccess().setPayload(response).setMessage("update successfully.");
            }else {
                return Response.<User>ok().setMessage("user with "+id+" not found").setSuccess(false);
            }
        }catch (Exception e){
            System.out.println("error:"+e);
            return Response.<User>exception().setMessage("Update Fail.").setSuccess(false);
        }
    }

    @DeleteMapping("/{id}")
    public Response<?> removeUser(@PathVariable int id){
        try {
            int result= userService.removeUser(id);
            if(result>0) {
                return Response.<Object>deleteSuccess().setMessage("One Row Is Delete.");
            }else {
                return Response.<Object>notFound().setMessage("user with"+id+" not found");
            }
        }catch (Exception e){
            return Response.<User>exception().setMessage("Remove Fail.").setSuccess(false);
        }

    }


}
