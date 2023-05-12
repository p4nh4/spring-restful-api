package com.api.springrestfulapi.repository;

import com.api.springrestfulapi.model.Transaction;
import com.api.springrestfulapi.model.UserTransaction;
import com.api.springrestfulapi.model.request.TransactionRequest;
import com.api.springrestfulapi.provider.TransactionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface TransactionRepository {
    @SelectProvider(type = TransactionProvider.class, method = "getAllTransactions")
    @Results({
            @Result(column = "sender_account_id", property ="senderID",one = @One(select = "getUserTransactionByAccountID")),
            @Result(column = "receiver_account_id", property ="receiverID" , one = @One(select="getUserTransactionByAccountID")),
            @Result(column = "transfer_at", property ="transferAt" )
    })
    List<Transaction> getAllTransactions();
    @Select("select a.id, ut.*, a.account_no from user_accounts\n" +
            "inner join users ut on ut.id = user_accounts.user_id\n" +
            "inner join accounts a on a.id = user_accounts.account_id\n" +
            "where account_id = #{id};")

    @Results(value = {
            @Result(property = "accountID", column = "id"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property = "user.id", column = "id"),
            @Result(property = "user.name", column = "name"),
            @Result(property = "user.gender", column = "gender"),
            @Result(property = "user.address", column = "address")
    })
    UserTransaction getUserTransactionByAccountID(int id);

    @InsertProvider(type = TransactionProvider.class, method = "createTransactions")
//    @Results({
//            @Result(column = "sender_account_id", property ="senderID",one = @One(select = "getUserTransactionByAccountID")),
//            @Result(column = "receiver_account_id", property ="receiverID" , one = @One(select="getUserTransactionByAccountID")),
//            @Result(column = "transfer_at", property ="transferAt" )
//    })
    int createTransactions(@Param("trans") TransactionRequest transaction);

    @DeleteProvider (type = TransactionProvider.class, method = "deleteTransaction")
    int deleteTransaction(int id);
}
