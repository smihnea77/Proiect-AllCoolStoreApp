package com.allcoolstore.security.config;

import com.allcoolstore.model.User;
import com.allcoolstore.security.adapter.UserDetailsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

public class SecurityConfig2 extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig2(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public UserDetailsManager userDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user1 = new UserDetailsAdapter(new User("David","Secheres","ds@gmail.com",
                "davids","1234","ADMIN",24,"0748","fratiei"));
//        UserDetails user2 = new UserDetailsAdapter(new User("cc",
//                "user2",
//                "123465",
//                "CUSTOMER",
//                "cc",
//                "customer@yahoo.com",
//                "0000"));

        manager.createUser(user1);
//        manager.createUser(user2);
        return manager;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests()
                .mvcMatchers("/createOrder").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/orderlist").permitAll()
                .anyRequest().authenticated();

    }
}