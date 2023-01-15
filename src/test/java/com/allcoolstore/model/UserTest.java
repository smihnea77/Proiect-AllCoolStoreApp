package com.allcoolstore.model;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void constructorUserTest () {
        //given
        String firstName = "John";
        String lastName = "Doe";
        Date dateOfBirth = null;
        String email = "john_doe@gmail.com";
        String username = "johndoe";
        String password = "pass";
        String role = "admin";
        String phone = "001111111";
        String city = "New York";
        String county = "New York";
        String postalCode = "000001";
        String address1 = "11, Washington street";
        String address2 = null;

        //when
        User expectedUser = new User(firstName, lastName, dateOfBirth, email, username, password, role, phone, city, county, postalCode, address1, address2);

        //then
        assertEquals(firstName, expectedUser.getFirstName());
        assertEquals(lastName, expectedUser.getLastName());
        assertEquals(dateOfBirth, expectedUser.getDateOfBirth());
        assertEquals(email, expectedUser.getEmail());
        assertEquals(username, expectedUser.getUsername());
        assertEquals(password, expectedUser.getPassword());
        assertEquals(role, expectedUser.getRole());
        assertEquals(phone, expectedUser.getPhone());
        assertEquals(city, expectedUser.getCity());
        assertEquals(county, expectedUser.getCounty());
        assertEquals(postalCode, expectedUser.getPostalCode());
        assertEquals(address1, expectedUser.getAddress1());
        assertEquals(address2, expectedUser.getAddress2());
        assertNotEquals(firstName, expectedUser.getLastName());
        assertNotNull(expectedUser);

    }




}
