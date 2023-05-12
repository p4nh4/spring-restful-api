package com.api.springrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTransaction {
    private int accountID;
    private String accountNumber;
    private User user;
}
