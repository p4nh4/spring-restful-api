package com.api.springrestfulapi.provider;


import org.apache.ibatis.jdbc.SQL;

public class TransactionProvider {
    public static String getAllTransactions()
    {
            return new SQL()
            {
                {
                    SELECT("*");
                    FROM ("transaction");
                }
            }.toString();
    }
    public static String createTransactions()
    {
        return new SQL()
        {
            {
                INSERT_INTO("transaction");
                VALUES("sender_account_id","#{trans.senderID}");
                VALUES("receiver_account_id","#{trans.receiverID}");
                VALUES("amount","#{trans.amount}");
                VALUES("remark","#{trans.remark}");
                VALUES("transfer_at","#{trans.transferAt}");
            }
        }.toString();
    }
    public static String deleteTransaction()
    {
        return new SQL()
        {
            {
                DELETE_FROM("transaction");
                WHERE("id=#{id}");
            }
        }.toString();
    }
}
