package com.example.ecomercesystem.Controller;
import com.example.ecomercesystem.ApiResponse.ApiResponse;
import com.example.ecomercesystem.Model.Product;
import com.example.ecomercesystem.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/getall")
    public ResponseEntity getAll()
    {
       return ResponseEntity.status(200).body(productService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors)
    {
        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(productService.CheckCategoryId(product)==false)
        {
            return ResponseEntity.status(400).body(new ApiResponse("Please enter exist Category Id"));
        }
       if(productService.addProduct(product))
       {
           return ResponseEntity.status(200).body(new ApiResponse("the Product is added"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("the Product Id is already exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id)
    {
        if (productService.deleteProduct(id))
        {
            return ResponseEntity.status(200).body(new ApiResponse("the Product is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("the Product not Found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product,Errors errors)
    {
        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(productService.CheckCategoryId(product)==false)
        {
            return ResponseEntity.status(400).body(new ApiResponse("Please enter exist Category Id"));
        }
        if (productService.updateProduct(product,id))
        {
            return ResponseEntity.status(200).body(new ApiResponse("the Product is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("the Product not Found"));
    }
    @GetMapping("/search/{id}")
    public ResponseEntity searchProduct(@PathVariable Integer id)
    {
        if(productService.searchProduct(id))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The Product is found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Product is Not found"));
    }

    //extra credit end point which is return all the product of specific category

    @GetMapping("/cproduct/{id}")
    public ResponseEntity getCategoryProducts(@PathVariable Integer id) {
    if (productService.CheckCategory(id)==false) {
        return ResponseEntity.status(400).body(new ApiResponse("The Category is not found"));
    }
        return ResponseEntity.status(200).body(productService.getCategoryProducts(id));
    }


}
