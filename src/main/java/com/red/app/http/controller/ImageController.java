package com.red.app.http.controller;

import com.red.model.Image;
import com.red.repository.image.ImageRepository;
import com.red.services.image.ImageService;
import com.red.system.filesystem.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @Autowired
    Storage storage;
    @Autowired
    ImageService imageService;

    @GetMapping("/product/image/{name}")
    public ResponseEntity<Resource> showImages(@PathVariable("name") String name) {
        if (storage.exists(name)) {
            return storage.download("upload/" + name);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/deleteimage/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") Long id){
        Image image = imageService.findById(id).get();
        String filePath = "upload/" + image.getFileName();
        if (storage.exists(filePath)) {
            storage.delete(filePath);
        }
        imageService.delete(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
