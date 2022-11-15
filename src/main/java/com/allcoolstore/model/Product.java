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
    private String name;
    private String type;
    private int qty;
    private double volume;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orderId = new HashSet<>();
}
