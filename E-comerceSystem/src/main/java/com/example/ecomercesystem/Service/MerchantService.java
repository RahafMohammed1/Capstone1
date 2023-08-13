package com.example.ecomercesystem.Service;

import com.example.ecomercesystem.Model.Merchant;
import com.example.ecomercesystem.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {
    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAll() {
        return merchants;
    }

    public boolean addMerchant(Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == merchant.getId()) {
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }

    public boolean deletingMerchant(Integer id) {

        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updatingMerchant(Integer id, Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }


    public boolean searchMerchant(Integer id)
    {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }

}
