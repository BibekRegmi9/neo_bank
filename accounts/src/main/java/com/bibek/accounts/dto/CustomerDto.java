package com.bibek.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",
    description = "Customer details"
)
public class CustomerDto {

    @Schema(description = "Customer name", example = "John Doe")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should have at minimum 2 characters and maximum 30 characters")
    private String name;

    @Schema(description = "Customer email", example = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile number format")
    private String mobileNumber;


    private AccountsDto accountsDto;
}
