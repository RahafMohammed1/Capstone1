package com.example.ecomercesystem.Service;

import com.example.ecomercesystem.Model.Merchant;
import com.example.ecomercesystem.Model.MerchantStock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final ProductService productService;
    private final MerchantService merchantService;

    public ArrayList<MerchantStock> getall() {
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == merchantStock.getId()) {
                return false;
            }
        }
        merchantStocks.add(merchantStock);
        return true;
    }

    public boolean checkProductId(MerchantStock merchantStock) {
        for (int i = 0; i < productService.getAll().size(); i++) {
            if (productService.getAll().get(i).getId() == merchantStock.getProductId())
                return true;
        }
        return false;
    }

    public boolean checkMerchantId(MerchantStock merchantStock) {
        for (int i = 0; i < merchantService.getAll().size(); i++) {
            if (merchantService.getAll().get(i).getId() == merchantStock.getMerchantId())
                return true;
        }
        return false;
    }


    public boolean deleteMerchantStock(Integer id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateMerchantStock(Integer id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean userAddMoreStock(Integer stock, Integer merchantId, Integer productid) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId() == merchantId) {
                if (merchantStocks.get(i).getProductId() == productid) {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + stock);
                    return true;
                }
            }
        }
        return false;
    }
}


