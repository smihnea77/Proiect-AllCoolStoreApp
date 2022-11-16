package com.allcoolstore.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   // @NotNull
    private String firstName;
    //NotNull
    private String lastName;
   // @NotNull
    private String email;
    //@NotNull
    private String username;
   // @NotNull
    private String password;
    //@NotNull
    private String role;
    //@NotNull
    private int age;
    private String phone;
    private String address;

    public User(String firstName, String lastName, String email, String username, String password, String role, int age, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;


}
