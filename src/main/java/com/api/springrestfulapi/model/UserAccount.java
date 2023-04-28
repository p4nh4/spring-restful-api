package com.api.springrestfulapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserAccount {
    private int id;
    private String name;
    private String gender;
    private String address;
    private List<Account> accounts;
}
