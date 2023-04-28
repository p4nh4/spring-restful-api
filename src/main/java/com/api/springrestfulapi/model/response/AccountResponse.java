package com.api.springrestfulapi.model.response;

import com.api.springrestfulapi.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private int id;
    private String account_no;
    private String account_name;
    private String profile;
//    private int pin;
//    private String password;
    private String phone_number;
    private int transfer_limit;
//    private int account_type;
    private AccountType account_type;
}
