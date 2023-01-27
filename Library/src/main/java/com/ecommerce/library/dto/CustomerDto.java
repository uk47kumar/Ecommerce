package com.ecommerce.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    @Size(min = 3, max = 10, message = "First name should be 3-10 characters !")
    private String firstName;
    @Size(min = 3, max = 10, message = "Last name should be 3-10 characters !")
    private String lastName;
    private String username;
    @Size(min = 3, max = 15, message = "Password should be 5-15 characters !")
    private String password;
    private String repeatPassword;
}
