package com.bibek.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should have at minimum 2 characters and maximum 30 characters")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number format")
    private String mobileNumber;


    private AccountsDto accountsDto;
}
