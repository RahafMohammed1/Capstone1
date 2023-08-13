package com.example.ecomercesystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "The id field is required")
    private Integer id;
    @NotEmpty(message = "The name field is required")
    @Size(min =4,message = "The name must to be more than 3 character")
    private String name;
    @NotNull(message = "The price field is required")
    @Positive(message = "The price must be positive integer")
    private Integer price;
    @NotNull(message = "The categoryID field is required")
    private Integer categoryID;
}
