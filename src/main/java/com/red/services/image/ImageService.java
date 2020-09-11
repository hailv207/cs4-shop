package com.red.services.image;

import com.red.model.Image;
import com.red.model.Product;
import com.red.services.IGeneralService;

public interface ImageService extends IGeneralService<Image> {
    Iterable<Image> findAllByProduct(Product product);
}
