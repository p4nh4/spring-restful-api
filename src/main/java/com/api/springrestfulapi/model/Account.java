package com.api.springrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String account_no;
    private String account_name;

    private String profile;
    private int pin;
    private String password;
    private String phone_number;
    private int transfer_limit;
    private AccountType accountType;
}
