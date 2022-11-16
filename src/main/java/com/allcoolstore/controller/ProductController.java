package com.allcoolstore.controller;

import com.allcoolstore.model.Product;
import com.allcoolstore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping(path = "update-product/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id, product);
    }

    @PostMapping(path = "/create-product")
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping(path = "{type}")
    public List <Product> getProductsByType(@PathVariable("type") String type){
        List <Product> findAllProducts = productService.getAllProducts();
       List <Product> productList = new ArrayList<>();
       for (Product p: findAllProducts){
           if (type.equals(p.getType())){
               productList.add(p);
           }
       }return productList;
    }

}
