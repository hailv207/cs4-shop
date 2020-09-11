package com.red.repository.product;

import com.red.model.Category;
import com.red.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    void deleteAllByCategory(Category category);
    Iterable<Product> findAllByCategory(Category category);
}
