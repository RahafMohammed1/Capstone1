package com.example.ecomercesystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "The Id field is required")
    private Integer id;
    @NotEmpty(message = "The name field is required")
    @Size(min =4,message = "The name must to be more than 5 character")
    private String Username;
    @NotEmpty(message = "The Password field is required")
    @Size(min = 7,message ="The Password have to be more than 6 length long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$",message = "the password must must have characters and digits ")
    private String password;
    @Email(message = "Please type a correct email")
    @NotEmpty(message = "The email field is required")
    private String email;
    @NotEmpty(message = "The Role field is required")
    @Pattern(regexp = "(Admin)|(admin)|(Customer)|(customer)",message ="The role have to be ( “Admin” or ”Customer”)")
    private String role;
    @NotNull(message = "The balance field is required")
    @Positive(message = "The balance must be Integer number  ")
    private Integer balance;
}
