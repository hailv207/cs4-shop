package com.red.services.product;

import com.red.model.Category;
import com.red.model.Product;
import com.red.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService extends IGeneralService<Product>  {
    void deleteAllByCategory(Category category);
    Iterable<Product> findAllByCategory(Category category);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    List<Product> findRandomByCategory(Category category, Product product, int limit);
    int countAllByCategory(Category category);
}
