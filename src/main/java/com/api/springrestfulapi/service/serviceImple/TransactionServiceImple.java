package com.api.springrestfulapi.service.serviceImple;

import com.api.springrestfulapi.model.Transaction;
import com.api.springrestfulapi.model.request.TransactionRequest;
import com.api.springrestfulapi.repository.TransactionRepository;
import com.api.springrestfulapi.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImple implements TransactionService {

    private final TransactionRepository transactionRepository;
    @Autowired
    TransactionServiceImple (TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public PageInfo<Transaction> getAllTransactions(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(transactionRepository.getAllTransactions());
    }
    //    @Override
//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.getAllTransactions();
//    }
    @Override
    public int createTransactions(TransactionRequest transactionRequest) {
        return transactionRepository.createTransactions(transactionRequest);
    }
    @Override
    public int deleteTranaction(int id) {
        return transactionRepository.deleteTransaction(id);
    }
    @Override
    public List<Transaction> getTransactionByUserID(int userID) {
        return null;
    }
}
