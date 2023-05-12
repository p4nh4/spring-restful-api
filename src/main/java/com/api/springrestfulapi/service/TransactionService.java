package com.api.springrestfulapi.service;

import com.api.springrestfulapi.model.Transaction;
import com.api.springrestfulapi.model.request.TransactionRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface TransactionService {
    //features
//    List<Transaction> getAllTransactions();
    PageInfo<Transaction> getAllTransactions(int page, int size);
    int createTransactions(TransactionRequest transactionRequest);
    int deleteTranaction(int transactionsID);
    List<Transaction> getTransactionByUserID(int userID);
}
