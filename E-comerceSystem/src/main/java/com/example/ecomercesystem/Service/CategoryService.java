package com.example.ecomercesystem.Service;

import com.example.ecomercesystem.Model.Category;
import com.example.ecomercesystem.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAll() {
        return categories;
    }

    //هنا أنشأتها من نوع boolean لأن أبغى أتأكد من Id اذا اضيف من قبل او لا
    public boolean addCategory(Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == category.getId())
                return false;
        }
        categories.add(category);
        return true;
    }

    public boolean deleteCategoryById(Integer id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateCategory(Integer id, Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }
    public boolean CheckTheCategory(Integer id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}

