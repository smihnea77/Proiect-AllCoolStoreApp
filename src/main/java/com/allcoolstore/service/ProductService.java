package com.allcoolstore.service;

import com.allcoolstore.model.Product;
import com.allcoolstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private double tva = 1.19;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        priceWithTva();
        return productRepository.findAll();
    }

//    public List<Product> getProductsByType(String type, Product product) {
//        priceWithTva();
//        List<Product> productListByType = (List<Product>) productRepository.findByType(type);
//        return productListByType;
//    }

    private void priceWithTva() {
        List<Product> productList = productRepository.findAll();
        for (Product p : productList) {
            p.setPrice(p.getPrice() * tva);
        }
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean productExists = productRepository.existsById(id);
        if (!productExists) {
            throw new IllegalStateException(String.format("Product with id %s does not exist.", id));
        }
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(String.format("Product with id %s does not exist.", id)));
        productToUpdate.setName(product.getName());
        productToUpdate.setType(product.getType());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setQty(product.getQty());
        productToUpdate.setVolume(product.getVolume());
        productRepository.save(productToUpdate);
    }
}

