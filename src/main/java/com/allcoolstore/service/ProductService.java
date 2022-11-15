package com.allcoolstore.service;

import com.allcoolstore.iservice.IProductService;
import com.allcoolstore.model.Product;
import com.allcoolstore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        boolean productExists = productRepository.existsById(id);
        if (!productExists){
            throw new IllegalStateException(String.format("Product with id %s does not exist.",id));
        }
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(()->
                new IllegalStateException(String.format("Product with id %s does not exist.",id)));
        productToUpdate.setName(product.getName());
        productToUpdate.setType(product.getType());
        productToUpdate.setQty(product.getQty());
        productToUpdate.setVolume(product.getVolume());
        productRepository.save(productToUpdate);
    }
    }

