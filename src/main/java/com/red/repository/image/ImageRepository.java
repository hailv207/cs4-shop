package com.red.repository.image;

import com.red.model.Image;
import com.red.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image,Long> {
   Iterable<Image> findAllByProduct(Product product);
}
