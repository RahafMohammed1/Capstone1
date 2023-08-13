package com.example.ecomercesystem.Service;

import com.example.ecomercesystem.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {
   private ArrayList<Product> products=new ArrayList<>();
   private final CategoryService categoryService;

    public ArrayList<Product> getAll()
    {
        return products;
    }
    //هنا هذي الميثود تتأكد لي من فئة المنتج المراد اضافته او تعديله إذا موجوده او لا

// هنا انشأتها من نوع boolean لأني ابغى Id ما يتكرر
    public boolean addProduct(Product product)
    {
        for (int i=0;i<products.size();i++)
        {
           if(products.get(i).getId()==product.getId())
               return false;
        }
        products.add(product);
        return true;
    }
    public boolean CheckCategoryId(Product product)
    {
      if(categoryService.CheckTheCategory(product.getCategoryID()))
        {
            return true;
        }
        return false;
    }
    public boolean deleteProduct(Integer id)
    {
        for (int i=0;i<products.size();i++)
        {
            if(products.get(i).getId()==id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct (Product product,Integer id)
    {
        for (int i=0;i<products.size();i++)
        {
            if(products.get(i).getId()==id) {
                products.set(i,product);
                return true;
            }
        }
        return false;
    }
    //extra credit
    public boolean searchProduct(Integer id)
    {
        for (int i=0;i<products.size();i++)
        {
            if(products.get(i).getId()==id) {
                return true;
            }
        }
        return false;
    }

    //extra credit end point which is return all the product of specific category
    public boolean CheckCategory(Integer id)
    {
        if(categoryService.CheckTheCategory(id))
        {
            return true;
        }
        return false;
    }
    public ArrayList<Product> getCategoryProducts(Integer id)
    {
        ArrayList<Product> product1=new ArrayList<>();
        for(int j=0;j<products.size();j++)
        {
            if(products.get(j).getCategoryID()==id) {
                product1.add(products.get(j));
            }
        }
        return product1;
    }


    //extra credit/i built a method delete all products following specific category
    public boolean DeleteProductsOfCategory(Integer id)
    {
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).getCategoryID()==id)
            {
                deleteProduct(products.get(i).getId());
                return true;
            }
        }
        return false;
    }
}

