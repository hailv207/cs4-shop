package com.red.app.http.controller.product;

import com.red.model.Category;
import com.red.model.Image;
import com.red.model.Product;
import com.red.repository.users.CategoryRepository;
import com.red.services.image.ImageService;
import com.red.services.image.ImageServiceImpl;
import com.red.services.product.ProductService;
import com.red.system.filesystem.Storage;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductManagerController {
    @Autowired
    private Storage storage;

    @Autowired
    Hashids hashids;

    @Autowired
    ImageService imageService;


    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute("category")
    public Iterable<Category> categories() {
        return categoryRepository.findAll();
    }


    @GetMapping("/admin")
    public ModelAndView listProduct() {
        ModelAndView modelAndView = new ModelAndView("/productManager/product-manager");
        return modelAndView;
    }

    @GetMapping("/admin/product-manager")
    public ModelAndView showProductManager() {
        Iterable<Product> product = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/admin/products-manager");
        modelAndView.addObject("products", product);
        return modelAndView;
    }

    @GetMapping("/admin/create-product")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/admin/create-product");
        Iterable<Category> categories = categoryRepository.findAll();
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/admin/create-product")
    public String createProduct(@ModelAttribute("product") Product product,
                                @RequestParam("files") MultipartFile[] files,
                                RedirectAttributes redirectAttributes) {
        productService.save(product);
        for (MultipartFile file : files) {
            if (file.getSize() > 0){
                Image image = new Image();
                image.setProduct(product);
                imageService.save(image);
                String nameFile = file.getOriginalFilename();
                String extension = nameFile.substring(nameFile.lastIndexOf("."));
                image.setFileName(hashids.encode(image.getId()) + extension);
                imageService.save(image);
                storage.putFile(file, "upload", image.getFileName());
            }
        }

        redirectAttributes.addFlashAttribute("create_done", true);
        return "redirect:/admin/product-manager";
    }

    @GetMapping("/admin/edit-product/{id}")
    public ModelAndView showEditProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/edit-product");
            modelAndView.addObject("product", product.get());
            Iterable<Image> images = imageService.findAllByProduct(product.get());
            modelAndView.addObject("images", images);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }


    @PostMapping("/admin/edit-product/{id}")
    public String editProduct(@ModelAttribute("product") Product product,
                              @RequestParam("files") MultipartFile[] files,
                              RedirectAttributes redirectAttributes)
    {
        productService.save(product);

        for (MultipartFile file : files) {
            if (file.getSize() > 0){
                Image image = new Image();
                image.setProduct(product);
                imageService.save(image);
                String nameFile = file.getOriginalFilename();
                String extension = nameFile.substring(nameFile.lastIndexOf("."));
                image.setFileName(hashids.encode(image.getId()) + extension);
                imageService.save(image);
                storage.putFile(file, "upload", image.getFileName());
            }
        }

        redirectAttributes.addFlashAttribute("edit_done",true);
        return "redirect:/admin/product-manager";
    }


    @GetMapping("/admin/delete-product/{id}")
    public ModelAndView showDeleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/admin/delete-product");
            modelAndView.addObject("product", product);
            Category category = product.get().getCategory();
            modelAndView.addObject("cat",category);
            Iterable<Image> images = imageService.findAllByProduct(product.get());
            modelAndView.addObject("images", images);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/404");
            return modelAndView;
        }
    }

    @PostMapping("/admin/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        Iterable<Image> iterable = imageService.findAllByProduct(product);
        for (Image image : iterable) {
            String filePath = "upload/" + image.getFileName();
            if (storage.exists(filePath)) {
                storage.delete(filePath);
            }
            imageService.delete(image);
        }
        productService.delete(product);
        redirectAttributes.addFlashAttribute("delete_done", true);
        return "redirect:/admin/product-manager";
    }
}
