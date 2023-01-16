package com.allcoolstore.model;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void constructorProductTest() {

        //given
        String name = "Ursus";
        String name2 = "Silva";
        String producer = "Breweries";
        String type = "beer";
        double price = 4.50;
        int qty = 45;
        double bottleSize = 0.33;
        String description = "bere";
        String image = null;
        //when
        Product expectedProduct = new Product(name, producer, type, price, qty, bottleSize, description, image);

        //then
        assertEquals(name, expectedProduct.getName());
        assertEquals(producer, expectedProduct.getProducer());
        assertEquals(type, expectedProduct.getType());
        assertEquals(price, expectedProduct.getPrice());
        assertEquals(qty, expectedProduct.getQty());
        assertEquals(bottleSize, expectedProduct.getBottleSize());
        assertEquals(description, expectedProduct.getDescription());
        assertEquals(image, expectedProduct.getImage());
        assertNotEquals(name2, expectedProduct.getName());
        assertNotNull(expectedProduct);

    }

}
