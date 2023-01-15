package com.allcoolstore.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull
    private String name;
    //@NotNull
    private String producer;
    private String type;
    //@NotNull
    private double price;
    private int qty;
    private double bottleSize;
    private String description;
    @Lob
    @Column(
            name = "photo",
            columnDefinition = "MEDIUMBLOB"
    )
    private String image;

    public Product(String name, String producer, String type, double price, int qty, double bottleSize, String description, String image) {
        this.name = name;
        this.producer = producer;
        this.type = type;
        this.price = price;
        this.qty = qty;
        this.bottleSize = bottleSize;
        this.description = description;
        this.image = image;
    }

    public Product(String name, String producer, String type) {
        this.name = name;
        this.producer = producer;
        this.type = type;
    }

    @ManyToMany(mappedBy = "products")
    private Set<Order> orderId = new HashSet<>();
}
