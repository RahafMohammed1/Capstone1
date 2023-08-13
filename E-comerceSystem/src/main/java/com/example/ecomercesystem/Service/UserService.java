package com.example.ecomercesystem.Service;

import com.example.ecomercesystem.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();
    private final MerchantService merchantService ;
    private final ProductService productService ;
    private final MerchantStockService merchantStockService;

    public ArrayList<User> getall() {
        return users;
    }

    public boolean addUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public boolean deleteUser(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateUser(Integer id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean searchUser(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMerchantId(Integer merchantId) {
        if (merchantService.searchMerchant(merchantId)) {
            return true;
        }
        return false;
    }

    public boolean checkProductId(Integer productId )
    {
        if (productService.searchProduct(productId)) {
            return true;
        }
        return false;
    }
    public boolean isMerchantHasProductInTheStock(Integer merchantId,Integer productId)
    {
       for(int i=0;i<merchantStockService.getall().size();i++){
           if(merchantStockService.getall().get(i).getMerchantId()==merchantId)
           {
               if (merchantStockService.getall().get(i).getProductId()== productId)
               {
                   if(merchantStockService.getall().get(i).getStock()>1)
                   {
                       return true;
                   }
               }
           }
       }
       return false;
    }



    // Integer deducted= user.getBalance()-productService.getAll().get(i).getPrice();
    public boolean userBuyProduct(Integer productId,Integer UserId) {
        User user = null;
        Integer index=0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == UserId) {
                 user= users.get(i);
                 index=i;
            }
        }
        for(int i=0;i<productService.getAll().size();i++)
        {
            if(productService.getAll().get(i).getId()==productId)
            {
                if(productService.getAll().get(i).getPrice()>user.getBalance()){
                    return false;
                }
                user.setBalance(user.getBalance()-productService.getAll().get(i).getPrice());
                users.set(index,user);
            }
        }
        return true;
    }
    }

