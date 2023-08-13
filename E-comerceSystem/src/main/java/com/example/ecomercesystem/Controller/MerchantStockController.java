package com.example.ecomercesystem.Controller;

import com.example.ecomercesystem.ApiResponse.ApiResponse;
import com.example.ecomercesystem.Model.MerchantStock;
import com.example.ecomercesystem.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchantstock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping("/getall")
    public ResponseEntity getAll()
    {
        return ResponseEntity.status(200).body(merchantStockService.getall());
    }
    //in Adding process i first check the merchant Id  and Product Id
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors)
    {
        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(merchantStockService.checkMerchantId(merchantStock)==false)
        {
            return ResponseEntity.status(400).body(new ApiResponse("The merchant Id is not exist"));
        }
        if(merchantStockService.checkProductId(merchantStock)==false)
        {
            return ResponseEntity.status(400).body(new ApiResponse("The Product Id is not exist"));
        }
        if(merchantStockService.addMerchantStock(merchantStock))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The Merchant Stock is added"));
        }
       return ResponseEntity.status(400).body(new ApiResponse("The merchant stock is already exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id)
    {
        if(merchantStockService.deleteMerchantStock(id))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The merchant Stock is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The merchant Stock is Not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@Valid@RequestBody MerchantStock merchantStock,@PathVariable Integer id,Errors errors) {
        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(merchantStockService.updateMerchantStock(id,merchantStock))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The merchant stock is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The merchant Stock is Not found"));
    }
    @PutMapping("/addstock/{merchantid}/{productid}/{amount}")
    public ResponseEntity userAddMoreStock(@PathVariable Integer merchantid,@PathVariable Integer productid,@PathVariable Integer amount )
    {
        if(merchantStockService.userAddMoreStock(amount,merchantid,productid))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The more Stocks is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The merchant Stock is Not found please enter correct merchant id,product id"));
    }

}
