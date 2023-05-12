package com.api.springrestfulapi.model.request;

import com.api.springrestfulapi.model.UserTransaction;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TransactionRequest {

    @NotBlank(message = "have to insert Id!")
    private UserTransaction senderID;
    @NotBlank(message = "have to insert Id!")
    private UserTransaction receiverID;
    @NotBlank(message = "have to insert amount!")
    private float amount;


}
