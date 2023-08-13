package com.example.ecomercesystem.Controller;

import com.example.ecomercesystem.ApiResponse.ApiResponse;
import com.example.ecomercesystem.Model.Category;
import com.example.ecomercesystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getall")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(categoryService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       if(categoryService.addCategory(category))
           return ResponseEntity.status(200).body(new ApiResponse("The Category is added"));
       return ResponseEntity.status(400).body(new ApiResponse("The Category Id is already exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        if(categoryService.deleteCategoryById(id))
            return ResponseEntity.status(200).body(new ApiResponse("The Category is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("The Category is not found"));

}

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id,@Valid @RequestBody Category category,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (categoryService.updateCategory(id, category))
        {
            return ResponseEntity.status(200).body(new ApiResponse("The Category is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("The Category is not found"));
    }

}
