package com.example.ecomercesystem.Controller;

import com.example.ecomercesystem.ApiResponse.ApiResponse;
import com.example.ecomercesystem.Model.Merchant;
import com.example.ecomercesystem.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;
        @GetMapping("/getall")
        public ResponseEntity getall()
         {
             return ResponseEntity.status(200).body(merchantService.getAll());
         }

         @PostMapping("/add")
         public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors)
         {
             if (errors.hasErrors())
             {
                 String message=errors.getFieldError().getDefaultMessage();
                 return ResponseEntity.status(400).body(message);
             }
             if(merchantService.addMerchant(merchant))
             {
                 return ResponseEntity.status(200).body(new ApiResponse("The merchant is added"));
             }
             return ResponseEntity.status(400).body(new ApiResponse("The merchant is already exist"));
         }
         @DeleteMapping("/delete/{id}")
         public ResponseEntity deleteMerchant(@PathVariable Integer id)
         {
             if(merchantService.deletingMerchant(id))
             {
                 return ResponseEntity.status(200).body(new ApiResponse("The merchant is deleted"));
             }
             return ResponseEntity.status(400).body(new ApiResponse("The merchant is Not found"));
         }
         @PutMapping("/update/{id}")
         public ResponseEntity updateMerchant(@Valid@RequestBody Merchant merchant,@PathVariable Integer id,Errors errors)
         {
             if (errors.hasErrors())
             {
                 String message=errors.getFieldError().getDefaultMessage();
                 return ResponseEntity.status(400).body(message);
             }
             if(merchantService.updatingMerchant(id,merchant))
             {
                 return ResponseEntity.status(200).body(new ApiResponse("The merchant is updated"));
             }
             return ResponseEntity.status(400).body(new ApiResponse("The merchant is Not found"));
         }
         @GetMapping("/search/{id}")
         public ResponseEntity searchMerchant(@PathVariable Integer id)
         {
             if(merchantService.searchMerchant(id))
             {
                 return ResponseEntity.status(200).body(new ApiResponse("The merchant is found"));
             }
             return ResponseEntity.status(400).body(new ApiResponse("The merchant is Not found"));
         }


}
