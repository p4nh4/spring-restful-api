package com.api.springrestfulapi.controller;

import com.api.springrestfulapi.model.Transaction;
import com.api.springrestfulapi.model.User;
import com.api.springrestfulapi.model.request.TransactionRequest;
import com.api.springrestfulapi.model.request.UserRequest;
import com.api.springrestfulapi.service.TransactionService;
import com.api.springrestfulapi.util.Response;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private TransactionService transactionService;
    @Autowired
    TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }
    @GetMapping("/all-transactions")
    public Response<PageInfo<Transaction>> getAllTransactions(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size)
    {
        PageInfo<Transaction> transactions = transactionService.getAllTransactions(page,size);
        return Response. <PageInfo<Transaction>>ok().setPayload(transactions)
                .setMessage("retrieved...!");
    }
    @PostMapping("/newtransaction")
    public Response<Transaction> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest)
    {
        try{

            int rowE = transactionService.createTransactions(transactionRequest);
            if(rowE>0)
            {
                Transaction res = new Transaction().setSenderID(transactionRequest.getSenderID())
                        .setReceiverID(transactionRequest.getReceiverID())
                        .setAmount(transactionRequest.getAmount())
                        .setId(rowE);

                return Response.<Transaction>createSuccess().setPayload(res).setMessage("created!").setSuccess(true);
            }else {
                return Response.<Transaction>badRequest().setMessage("failed to create!");
            }
        }catch (Exception e)
        {
            return Response.<Transaction>exception().setMessage("failed to create!")
                    .setSuccess(false);
        }
    }

    @DeleteMapping("/{id}")
    public Response<?> deleteUser(@PathVariable int id) {
        try {
            int affectedRow = transactionService.deleteTranaction(id);
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
