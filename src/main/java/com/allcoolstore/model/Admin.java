package com.allcoolstore.model;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Order> orderList;

    @OneToMany(mappedBy = "admin")
    private List<Product> productList;

}
