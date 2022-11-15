package com.allcoolstore.iservice;
import com.allcoolstore.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();
    void createProduct(Product product);
    void deleteProduct(Long id);
    void updateProduct(Long id, Product product);
}
