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

    //private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir");

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView("products");
        List<Product> productList = productService.getAllProducts();
        modelAndView.addObject("productList", productList);
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
                                      @RequestParam("price") double price,
                                      @RequestParam("type") String type) {
        productService.saveProduct2ToDB(file, name, price, type);
        //return new ModelAndView("redirect:/view-product");
        return new ModelAndView("redirect:/");
    }

    @PostMapping(path = "update-product/{id}")
    public ModelAndView updateProduct(@PathVariable Long id, @ModelAttribute("productUpdateForm") Product product) {
        productService.updateProduct(id, product);
        return new ModelAndView("redirect:/");
    }


    @GetMapping("update-product/{id}")
    public ModelAndView updateProductPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("updateProduct");
        modelAndView.addObject(productService.getByProductId(id));
        return modelAndView;
    }

    //    @DeleteMapping(path = "/delete-product/{id}")
//    public ModelAndView deleteProduct(@PathVariable Long id, @ModelAttribute("productUpdateForm") Product product) {
//        productService.deleteProduct(id);
//        return new ModelAndView("redirect:/");
//    }
    @GetMapping(path = "/delete-product/{id}")
    public ModelAndView deleteProduct(@PathVariable ("id") Long id, Model model) {
        productService.deleteProduct(id);
        return new ModelAndView("redirect:/");
    }
//    @GetMapping("/delete-product/{id}")
//    public ModelAndView deleteProductPage(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("deleteProduct");
//        modelAndView.addObject(productService.getByProductId(id));
//        return modelAndView;
//    }


    @GetMapping(path = "/cognac")
    public String getCognacType(ModelMap modelMap) {
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
    public String getChampagneType(ModelMap modelMap) {
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
    public String getRumType(ModelMap modelMap) {
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
    public String getVodkaType(ModelMap modelMap) {
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
    public String getWhiskeyType(ModelMap modelMap) {
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
    public String getWineType(ModelMap modelMap) {
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
