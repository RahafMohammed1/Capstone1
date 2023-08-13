package com.example.ecomercesystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "The id field is required" )
    private Integer id;
    @NotEmpty(message = "The name field is required")
    @Size(min =4,message = "The name must to be more than 3 character")
    private String name;
}
