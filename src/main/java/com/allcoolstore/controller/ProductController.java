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
        List<Product> productList = productService.getAllProducts();
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @GetMapping(path = "/product/{id}")
    public ModelAndView getProductById(@PathVariable("id") Long id, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> findAllProducts = (List<Product>) productService.getByProductId(id);
        List<Product> productList = new ArrayList<>();
        for (Product p : findAllProducts) {
            if (id.equals(p.getId())) {
                productList.add(p);
            }
        }if (productList.isEmpty()){
            throw new IllegalStateException(String.format("No id of %s found. Please try again with a valid type.", id));
        }
        modelMap.addAttribute("products", productList);
        return modelAndView;
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
        //return new ModelAndView("redirect:/view-product");
        return new ModelAndView("redirect:/products/products");
    }

    @PostMapping(path = "update-product/{id}")
    public ModelAndView updateProduct(@RequestParam("file") MultipartFile file, @PathVariable Long id, @ModelAttribute("productUpdateForm") Product product) {
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
            throw new IllegalStateException(String.format("No type of %s found. Please try again with a valid type.", type));
        }
        modelMap.addAttribute("products", productList);
        return modelAndView;
    }


//    @GetMapping(path = "/cognac")
//    public ModelAndView getCognacType(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView("cognac");
//        String type = "cognac";
//        List<Product> findAllProducts = productService.getAllProducts();
//        List<Product> productList = new ArrayList<>();
//        for (Product p : findAllProducts) {
//            if (type.equals(p.getType())) {
//                productList.add(p);
//            }
//        }
//        modelMap.addAttribute("products", productList);
//        return modelAndView;
//    }

//    @GetMapping(path = "/champagne")
//    public ModelAndView getChampagneType(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView("champagne");
//        String type = "champagne";
//        List<Product> findAllProducts = productService.getAllProducts();
//        List<Product> productList = new ArrayList<>();
//        for (Product p : findAllProducts) {
//            if (type.equals(p.getType())) {
//                productList.add(p);
//            }
//        }
//        modelMap.addAttribute("products", productList);
//        return modelAndView;
//    }


//    @GetMapping(path = "/rum")
//    public ModelAndView getRumType(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView("rum");
//        String type = "rum";
//        List<Product> findAllProducts = productService.getAllProducts();
//        List<Product> productList = new ArrayList<>();
//        for (Product p : findAllProducts) {
//            if (type.equals(p.getType())) {
//                productList.add(p);
//            }
//        }
//        modelMap.addAttribute("products", productList);
//        return modelAndView;
//    }

//    @GetMapping(path = "/vodka")
//    public ModelAndView getVodkaType(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView("vodka");
//        String type = "vodka";
//        List<Product> findAllProducts = productService.getAllProducts();
//        List<Product> productList = new ArrayList<>();
//        for (Product p : findAllProducts) {
//            if (type.equals(p.getType())) {
//                productList.add(p);
//            }
//        }
//        modelMap.addAttribute("products", productList);
//        return modelAndView;
//    }

//    @GetMapping(path = "/whiskey")
//    public ModelAndView getWhiskeyType(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView("whiskey");
//        String type = "whiskey";
//        List<Product> findAllProducts = productService.getAllProducts();
//        List<Product> productList = new ArrayList<>();
//        for (Product p : findAllProducts) {
//            if (type.equals(p.getType())) {
//                productList.add(p);
//            }
//        }
//        modelMap.addAttribute("products", productList);
//        return modelAndView;
//    }

//    @GetMapping(path = "/wine")
//    public ModelAndView getWineType(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView("wine");
//        String type = "wine";
//        List<Product> findAllProducts = productService.getAllProducts();
//        List<Product> productList = new ArrayList<>();
//        for (Product p : findAllProducts) {
//            if (type.equals(p.getType())) {
//                productList.add(p);
//            }
//        }
//        modelMap.addAttribute("products", productList);
//        return modelAndView;
//    }



    //    @GetMapping(path = "/{type}")
//    public String getProductsByType(@PathVariable("type") String type, ModelMap modelMap){
//        List <Product> findAllProducts = productService.getAllProducts();
//        List <Product> productList = new ArrayList<>();
//        for (Product p: findAllProducts){
//            if (type.equals(p.getType())){
//                productList.add(p);
//            }
//        }modelMap.addAttribute("products", productList);
//        return "view";



}