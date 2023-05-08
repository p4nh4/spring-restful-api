package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.UserAccount;
import com.api.springrestfulapi.model.request.UserRequest;
import com.api.springrestfulapi.service.UserService;
import com.api.springrestfulapi.util.Response;
import com.github.pagehelper.PageInfo;
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
        public Response<PageInfo<User>> getAllUser(@RequestParam (defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "", required = false) String  username) {
        try {
            PageInfo<User> response = userService.allUsers(page, size, username);
            return Response.<PageInfo<User>>ok().setPayload(response).setMessage("Successfully retrieved all users ! ");


        } catch (Exception e) {
//                return Response.<List<User>>exception().setMessage("retrieved failed LMAO");
            System.out.println("Exception : " + e.getMessage());
            return Response.<PageInfo<User>>exception().setMessage("Failed to retrieved the users ! Exception occurred ! ");
        }
    }
//    @PutMapping("/{id}")
//    User updateByID(@RequestBody UserRequest request,@PathVariable("id") int id){
//        try {
//            int result= userService.updateUser(request,id);
//            if(result>0) {
//                User response = new User().setId(id).setName(request.getName()).setGender(request.getGender()).setAddress(request.getAddress());
//                return Response.<PageInfo<User>>updateSuccess().setPayload(response).setMessage("update successfully ! ").getPayload();
////                return Response.<User>updateSuccess().setPayload(response).setMessage("update successfully.");
//            }else {
//                return Response.<PageInfo<User>>ok().setMessage("user with "+id+" not found").setSuccess(false).getPayload();
//            }
//        }catch (Exception e){
//            System.out.println("error:"+e);
//            return Response.<PageInfo<User>>exception().setMessage("Update Fail.").setSuccess(false).getPayload();
//        }
//    }


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
    public Response<?> deleteUser(@PathVariable int id) {
        try {
            int affectedRow = userService.removeUser(id);
            if (affectedRow > 0) {
                // delete success
                return Response.<Object>deleteSuccess().setMessage("Successfully remove the user ! ").setSuccess(true);
            } else {
                // id do not exist !
                return Response.<Object>notFound().setMessage("User with id =" + id + " doesn't exist in our system !");
            }

        } catch (Exception ex) {
            return Response.<Object>exception().setMessage("Exception occurred! Failed to delete the user !").setSuccess(false);
        }
    }
}
