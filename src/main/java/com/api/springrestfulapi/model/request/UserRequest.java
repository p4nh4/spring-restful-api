package com.api.springrestfulapi.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserRequest {
    @NotBlank(message = "name is required!")
    private String name;
    @NotBlank(message = "gender is also required!")
    private String gender;
    private String address;
}
