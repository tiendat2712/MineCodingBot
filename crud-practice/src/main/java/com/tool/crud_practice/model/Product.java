package com.tool.crud_practice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "BRAND", nullable = false)
    String brand;

    @Column(name = "CATEGORY", nullable = false)
    String category;

    @Column(name = "PRICE", nullable = false)
    double price;

    @Column(name = "DESCRIPTION", nullable = false)
    String description;

    @Column(name = "CREATED_AT", nullable = false)
    Date createdAt;

    @Column(name = "IMAGE_PATH", nullable = false)
    String imageFileName;


}
