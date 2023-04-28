package com.api.springrestfulapi.mapper;

import com.api.springrestfulapi.model.Account;
import com.api.springrestfulapi.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {
    //account -> accountresponse
    List<AccountResponse> mapToAccountResponse(List<Account> accounts);
    //accountresponse -> account
    List<Account> mapToAccount (List<AccountResponse> responses);
}
