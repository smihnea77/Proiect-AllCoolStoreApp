package com.allcoolstore.service;

import com.allcoolstore.model.Product;
import com.allcoolstore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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

    public void saveProductToDB(MultipartFile file, String name, String type, double price, int qty, double volume) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(name);
        product.setPrice(price);
        product.setType(type);
        product.setQty(qty);
        product.setVolume(volume);
        productRepository.save(product);
    }

    public void saveProduct2ToDB(MultipartFile file, String name, double price, String type) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(name);
        product.setPrice(price);
        product.setType(type);
        productRepository.save(product);
    }
}

