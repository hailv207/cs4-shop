package com.red.services.product;

import com.red.model.Product;
import com.red.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product entity) {
        productRepository.save(entity);
    }

    @Override
    public void update(Product entity) {
        productRepository.save(entity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }
}
