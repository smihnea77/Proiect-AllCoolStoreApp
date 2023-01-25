package com.allcoolstore.controller;

import com.allcoolstore.model.Product;
import com.allcoolstore.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView("products");
        List<Product> productList = productService.getAllProductsForAdmin();
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView getProductById(@PathVariable("id") Long id, Model modelMap) {
        productService.getByProductId(id);
        return new ModelAndView("redirect:/products/product");
    }



    @GetMapping("/create-product")
    public ModelAndView addProductPage() {
        ModelAndView modelAndView = new ModelAndView("createProduct");
        modelAndView.addObject(new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@RequestParam("file") MultipartFile file,
                                      @RequestParam("name") String name,
                                      @RequestParam("producer") String producer,
                                      @RequestParam("type") String type,
                                      @RequestParam("price") double price,
                                      @RequestParam("qty") int qty,
                                      @RequestParam("bottleSize") double bottleSize,
                                      @RequestParam("description") String description) {
        productService.createProduct(file,name,producer,type,price,qty,bottleSize,description);
        return new ModelAndView("redirect:/products/products");
    }

    @PostMapping(path = "update-product/{id}")
    public ModelAndView updateProduct(@RequestParam("file") MultipartFile file, @PathVariable Long id,
                                      @ModelAttribute("productUpdateForm") Product product) {
        productService.updateProduct(file, id, product);
        return new ModelAndView("redirect:/products/products");
    }


    @GetMapping("update-product/{id}")
    public ModelAndView updateProductPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("updateProduct");
        modelAndView.addObject(productService.getByProductId(id));
        return modelAndView;
    }

    @GetMapping(path = "/delete-product/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id, Model model) {
        productService.deleteProduct(id);
        return new ModelAndView("redirect:/products/products");
    }

    @GetMapping(path = "/{type}")
    public ModelAndView getGenericType(@PathVariable("type") String type, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView(type);
        List<Product> findAllProducts = productService.getAllProducts();
        List<Product> productList = new ArrayList<>();
        for (Product p : findAllProducts) {
            if (type.equals(p.getType())) {
                productList.add(p);
            }
        }if (productList.isEmpty()){
            throw new IllegalStateException(String.format("No type of %s found. Please try again with a valid type.",
                    type));
        }
        modelMap.addAttribute("products", productList);
        return modelAndView;
    }
    @GetMapping("/products/cognac/{id}")
    public ModelAndView openProductById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.getByProductId(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
}