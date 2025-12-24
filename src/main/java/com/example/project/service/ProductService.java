package com.example.project.service;


import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts()
    {
        return repo.findAll();
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }
}
