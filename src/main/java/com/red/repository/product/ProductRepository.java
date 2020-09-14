package com.red.repository.product;

import com.red.model.Category;
import com.red.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteAllByCategory(Category category);
    Iterable<Product> findAllByCategory(Category category);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE cat_id = :category AND id != :product ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Product> findRandomByCategory(@Param("category") Category category, @Param("product") Product product, @Param("limit") int limit);

    int countAllByCategory(Category category);
}
