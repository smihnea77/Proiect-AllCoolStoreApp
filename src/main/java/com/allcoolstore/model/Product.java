package com.allcoolstore.model;

import com.sun.istack.NotNull;
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
   // @NotNull
    private String name;
    //@NotNull
    private String type;
   // @NotNull
    private double price;
    private int qty;
    private double volume;


    @ManyToMany(mappedBy = "products")
    private Set<Order> orderId = new HashSet<>();
}
