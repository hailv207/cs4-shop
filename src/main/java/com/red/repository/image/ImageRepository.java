package com.red.repository.image;

import com.red.model.Image;
import com.red.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
   Iterable<Image> findAllByProduct(Product product);

   Optional<Image> findTopByProduct(Product product);
}
