package com.allcoolstore.service;

import com.allcoolstore.model.Product;
import com.allcoolstore.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;


    @Test
    void getAllProductsTest() {
        //given
        //when
        List<Product> actualProduct = productService.getAllProducts();
        //then
        assertNotNull(actualProduct);
    }

    @Test
    void getByProductIdTest() {
        //given
        Long givenProductId = Long.valueOf(10);
        Product mockedProduct = new Product("Silva", "Breweries", "beer", 10, 100, 10, "bere", "poza1" );
        //when
        when(productRepository.findById(givenProductId)).thenReturn(Optional.of(mockedProduct));

        Product actualProduct = productService.getByProductId(givenProductId);
        //then
        assertNotNull(actualProduct);
        assertEquals("Silva", actualProduct.getName());
    }

//    @Test
//    void deleteProductTest() throws Exception {
//        //given
//        Long givenId = Long.valueOf(20);
//        Product productToBeDeleted = new Product("Aurora", "Azuga", "beer", 25, 10, .7, "bere", "poza2");
//        //when
//        when(productRepository.findById(givenId)).thenReturn(Optional.of(productToBeDeleted));
//        productService.deleteProduct(givenId);
//        //then
//        verify(productRepository).delete(productToBeDeleted);
//    }

//    @Test
//    void saveProductToDBTest() {
//        //given
//        Long givenId = Long.valueOf(20);
//        MockMultipartFile file = new MockMultipartFile("file", "file.txt", MediaType.TEXT_PLAIN_VALUE, "FILE".getBytes());
//
//        Product productToBeSaved = new Product();
//        productToBeSaved.setName("Aurora");
//        productToBeSaved.setImage("file");
//        productToBeSaved.setProducer("Azuga");
//        productToBeSaved.setType("beer");
//        productToBeSaved.setPrice(5);
//        productToBeSaved.setQty(10);
//        productToBeSaved.setBottleSize(1);
//        productToBeSaved.setDescription("bere");
//        Product savedProduct = new Product();
//        savedProduct.setName("Aurora");
//        savedProduct.setImage("file");
//        savedProduct.setProducer("Azuga");
//        savedProduct.setType("beer");
//        savedProduct.setPrice(5);
//        savedProduct.setQty(10);
//        savedProduct.setBottleSize(1);
//        savedProduct.setDescription("bere");
//        //when
//        when(productRepository.save(productToBeSaved)).thenReturn(savedProduct);
//        //then
//        assertNotNull(savedProduct);
//    }






}
