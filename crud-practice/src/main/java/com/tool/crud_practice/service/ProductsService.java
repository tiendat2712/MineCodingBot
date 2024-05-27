package com.tool.crud_practice.service;

import com.tool.crud_practice.dto.ProductDto;
import com.tool.crud_practice.model.Product;
import com.tool.crud_practice.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Product> showProductList() {
        return productsRepository
                  .findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Product getProductById(int id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The product hasn't been existed !!"));
    }

    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        if(productsRepository.existsByName(productDto.getName())) {
            throw new RuntimeException("The product's name has been existed !!");
        }

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(new Date());
        product.setImageFileName(saveImageFile(productDto));

        return productsRepository.save(product);
    }

    // xử lí lưu đường dẫn file ảnh --> để set đường dẫn ảnh vào Product @Entity
    public String saveImageFile(ProductDto productDto) {
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
        String result;

        try{
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if(!(Files.exists(uploadPath))) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
                // Set imageFileName for the product
                return storageFileName;
            }
        } catch (Exception e){
            throw new RuntimeException("Failed to save image file.", e);
        }
    }

    // edit product details
    public Product editProduct(int productId, ProductDto productDto) {
        // Truy xuất đối tượng Product từ cơ sở dữ liệu
        Product product = productsRepository.findById(productId).orElse(null);

        if (product != null) {
            // Cập nhật thông tin của sản phẩm
            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            // Lưu lại sản phẩm đã cập nhật
            return productsRepository.save(product);
        } else {
            // Xử lý khi sản phẩm không tồn tại trong cơ sở dữ liệu
            return null;
        }
    }

    public void deleteProduct(Product product) {
        // Kiểm tra xem sản phẩm có tồn tại không
        if (productsRepository.existsById(product.getId())) {
            // Nếu tồn tại, xóa sản phẩm
            productsRepository.deleteById(product.getId());
        } else {
            // Nếu không tồn tại, ném một ngoại lệ
            throw new RuntimeException("Product with ID " + product.getId() + " does not exist.");
        }
    }



}
