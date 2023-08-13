package com.example.ecomercesystem.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "The id field is required" )
    private Integer id;
    @NotNull(message = "The product id field is required" )
    private Integer productId;
    @NotNull(message = "The merchant id field is required" )
    private Integer merchantId;
    @NotNull(message = "The stock field is required" )
    @Min(value = 10,message = "The Stock must be more than 10 ")
    private Integer stock;
}
