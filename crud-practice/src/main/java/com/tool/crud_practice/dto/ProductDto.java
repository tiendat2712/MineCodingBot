package com.tool.crud_practice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    @NotEmpty(message = "The name is required")
    String name;

    @NotEmpty(message = "The brand is required")
    String brand;

    @NotEmpty(message = "The category is required")
    String category;

    @Min(0)
    double price;

    @Size(min = 10, message = "The description should be at least 10 characters")
    @Size(max = 2000, message = "The description cannot exceed 2000 characters")
    String description;

    MultipartFile imageFile;

}
