package com.red.services.product;

import com.red.model.Category;
import com.red.model.Product;
import com.red.services.IGeneralService;


public interface ProductService extends IGeneralService<Product>  {
    void deleteAllByCategory(Category category);
    Iterable<Product> findAllByCategory(Category category);
}
