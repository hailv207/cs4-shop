package com.red.services.image;

import com.red.model.Image;
import com.red.model.Product;
import com.red.repository.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String default_thumb_150x75 = "/assets/img/150x75.jpg";

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public void save(Image entity) {
        imageRepository.save(entity);
    }

    @Override
    public void update(Image entity) {
        imageRepository.save(entity);
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void delete(Image entity) {
        imageRepository.delete(entity);
    }

    @Override
    public Iterable<Image> findAllByProduct(Product product) {
        return imageRepository.findAllByProduct(product);
    }

    @Override
    public String getThumbnail(Product entity){
        Optional<Image> optionalImage = imageRepository.findTopByProduct(entity);
        if (optionalImage.isPresent() && optionalImage.get().getFileName() != null){
            return "/upload/" + optionalImage.get().getFileName();
        }

        return default_thumb_150x75;
    }
}
