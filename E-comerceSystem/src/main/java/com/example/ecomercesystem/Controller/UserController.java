package com.example.ecomercesystem.Controller;

import com.example.ecomercesystem.ApiResponse.ApiResponse;
import com.example.ecomercesystem.Model.Merchant;
import com.example.ecomercesystem.Model.MerchantStock;
import com.example.ecomercesystem.Model.User;
import com.example.ecomercesystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/getall")
    public ResponseEntity getall()
    {
        return ResponseEntity.status(200).body(userService.getall());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.addUser(user)) {
            return ResponseEntity.status(200).body("The User Stock is added");
        }
        return ResponseEntity.status(400).body(new ApiResponse("The User is already exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id)
    {
        if(userService.deleteUser(id))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The User is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The User is Not found"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid@RequestBody User user,@PathVariable Integer id,Errors errors) {
        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(userService.updateUser(id,user))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The User is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The User is Not found"));
    }
    @PutMapping("/buyproduct/{userid}/{merchantid}/{productid}")
    public ResponseEntity userBuyProduct(@PathVariable Integer userid,@PathVariable Integer merchantid,@PathVariable Integer productid ) {
        if (userService.checkMerchantId(merchantid) == false) {
            return ResponseEntity.status(400).body(new ApiResponse("The Merchant is Not found"));
        }
        if (userService.searchUser(userid) == false) {
            return ResponseEntity.status(400).body(new ApiResponse("The User is Not found"));
        }
        if (userService.checkProductId(productid) == false) {
            return ResponseEntity.status(400).body(new ApiResponse("The Product is Not found"));
        }
        if(userService.isMerchantHasProductInTheStock(merchantid,productid)==false)
        {
            return ResponseEntity.status(400).body(new ApiResponse("the merchant has the product in stock"));
        }
        if(userService.userBuyProduct(productid,userid))
        {
            return ResponseEntity.status(200).body(new ApiResponse("Buy Process is done correctly"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("your Balance not enough"));
    }

}
