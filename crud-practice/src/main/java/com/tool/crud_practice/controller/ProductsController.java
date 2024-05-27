package com.tool.crud_practice.controller;

import com.tool.crud_practice.dto.ProductDto;
import com.tool.crud_practice.model.Product;
import com.tool.crud_practice.service.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    // get all products
    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = productsService.showProductList();
        model.addAttribute("products", products);
        return "products/index";
    }

    // switch to CreateProduct page
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }

    // create a new product
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
        if(productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError
                    ("productDto", "imageFile",
                    "The image file is required"));
        }

        if(result.hasErrors()) {
            return "products/CreateProduct";
        }
        productsService.createProduct(productDto);
        return "redirect:/products";
    }

    // switch to EditProduct page
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        try {
            Product product = productsService.getProductById(id);
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage",
                    "An error occurred while fetching product details."); // Thông báo cho người dùng
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    // edit the product details
    @PostMapping("/edit")
    public String updateProduct(Model model,
                                @RequestParam int id,
                                @Valid @ModelAttribute ProductDto productDto,
                                BindingResult result) {
        try {
            Product product = productsService.getProductById(id);
            model.addAttribute("product", product);

            if(result.hasErrors()) {
                return "products/EditProduct";
            }

            // change the old image !
            if (!productDto.getImageFile().isEmpty()) {
                // delete old image
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }

                // save new image file
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }
            productsService.editProduct(id, productDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        try {
            Product product = productsService.getProductById(id);

            // delete product image
            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // delete the product
            productsService.deleteProduct(product);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/products";
    }


}
