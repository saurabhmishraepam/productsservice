package com.epam.productsreview.service;

import com.epam.productsreview.entity.Product;
import com.epam.productsreview.exceptionHadlers.ProductsException;
import com.epam.productsreview.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by saurabh on 26/2/19.
 */
@Component
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public Product addProducts(Product product) {
        //validate
        return productsRepository.save(product);
    }

    public List<Product> getProducts() {
        List<Product> responselist = new ArrayList<>();
        productsRepository.findAll().forEach(prd -> responselist.add(prd));
        return responselist;
    }

    public Product getProductById(Long productId) {
        Optional<Product> product = productsRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductsException("Not found productId");
        }
        return product.get();
    }

    public void deleteProductById(Long productId) {
        productsRepository.deleteById(productId);
    }

    public void updateProduct(Product product) {

        productsRepository.save(product);

    }


}
