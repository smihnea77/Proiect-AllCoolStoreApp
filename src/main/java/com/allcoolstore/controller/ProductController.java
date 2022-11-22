package com.allcoolstore.controller;

import com.allcoolstore.model.Product;
import com.allcoolstore.service.ProductService;
import org.springframework.stereotype.Controller;
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

    //private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir");

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView("products");
        List<Product> productList = productService.getAllProducts();
        modelAndView.addObject("productList",productList);
        return modelAndView;
    }

        @PutMapping(path = "update-product/{id}")
        public void updateProduct (@PathVariable Long id, @RequestBody Product product){
            productService.updateProduct(id, product);
        }

        //    @PostMapping(path = "/create-product")
//    public void createProduct(@RequestBody Product product) {
//        productService.createProduct(product);
//    }
        @GetMapping("/create-product")
        public ModelAndView addProductPage () {
            ModelAndView modelAndView = new ModelAndView("createProduct");
            modelAndView.addObject(new Product());
            return modelAndView;
        }
        @PostMapping("/create-product")
        public String saveProduct (@RequestParam("file") MultipartFile file,
                @RequestParam("name") String name,
        @RequestParam("price") double price,
        @RequestParam("type") String type){
            productService.saveProduct2ToDB(file, name, price, type);
            //return new ModelAndView("redirect:/view-product");
            return "index";
        }

        @DeleteMapping(path = "{id}")
        public void deleteProduct (@PathVariable Long id){
            productService.deleteProduct(id);
        }

        @GetMapping(path = "/cognac")
        public String getCognacType (ModelMap modelMap){
            String type = "cognac";
            List<Product> findAllProducts = productService.getAllProducts();
            List<Product> productList = new ArrayList<>();
            for (Product p : findAllProducts) {
                if (type.equals(p.getType())) {
                    productList.add(p);
                }
            }
            modelMap.addAttribute("products", productList);
            return "cognac";
        }

        @GetMapping(path = "/champagne")
        public String getChampagneType (ModelMap modelMap){
            String type = "champagne";
            List<Product> findAllProducts = productService.getAllProducts();
            List<Product> productList = new ArrayList<>();
            for (Product p : findAllProducts) {
                if (type.equals(p.getType())) {
                    productList.add(p);
                }
            }
            modelMap.addAttribute("products", productList);
            return "champagne";
        }

        @GetMapping(path = "/rum")
        public String getRumType (ModelMap modelMap){
            String type = "rum";
            List<Product> findAllProducts = productService.getAllProducts();
            List<Product> productList = new ArrayList<>();
            for (Product p : findAllProducts) {
                if (type.equals(p.getType())) {
                    productList.add(p);
                }
            }
            modelMap.addAttribute("products", productList);
            return "rum";
        }

        @GetMapping(path = "/vodka")
        public String getVodkaType (ModelMap modelMap){
            String type = "vodka";
            List<Product> findAllProducts = productService.getAllProducts();
            List<Product> productList = new ArrayList<>();
            for (Product p : findAllProducts) {
                if (type.equals(p.getType())) {
                    productList.add(p);
                }
            }
            modelMap.addAttribute("products", productList);
            return "vodka";
        }

        @GetMapping(path = "/whiskey")
        public String getWhiskeyType (ModelMap modelMap){
            String type = "whiskey";
            List<Product> findAllProducts = productService.getAllProducts();
            List<Product> productList = new ArrayList<>();
            for (Product p : findAllProducts) {
                if (type.equals(p.getType())) {
                    productList.add(p);
                }
            }
            modelMap.addAttribute("products", productList);
            return "whiskey";
        }

        @GetMapping(path = "/wine")
        public String getWineType (ModelMap modelMap){
            String type = "wine";
            List<Product> findAllProducts = productService.getAllProducts();
            List<Product> productList = new ArrayList<>();
            for (Product p : findAllProducts) {
                if (type.equals(p.getType())) {
                    productList.add(p);
                }
            }
            modelMap.addAttribute("products", productList);
            return "wine";
        }
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


//        @GetMapping("/uploadimage")
//        public String displayUploadForm () {
//            return "index";
//        }
//
//        @PostMapping("/upload")
//        public String uploadImage (Model model, @RequestParam("image") MultipartFile file) throws IOException {
//            StringBuilder fileNames = new StringBuilder();
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//            fileNames.append(file.getOriginalFilename());
//            Files.write(fileNameAndPath, file.getBytes());
//            model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
//            return "index";
//        }


    }
