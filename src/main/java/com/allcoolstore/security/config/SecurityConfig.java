package com.allcoolstore.security.config;

import com.allcoolstore.repository.UserRepository;
import com.allcoolstore.service.PasswordService;
import com.allcoolstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordService passwordService;

    @Autowired
    UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().loginPage("/login").and().logout().logoutSuccessUrl("/logout").logoutUrl("/logout");
        http.authorizeRequests().mvcMatchers("/users/user").authenticated();
        http.authorizeRequests().mvcMatchers("users/delete-user/").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/users").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/products/update-product/").access("hasAnyAuthority('ADMIN')")
                .mvcMatchers("/products/delete-product/").access("hasAnyAuthority('ADMIN')");



//        http.formLogin().loginPage("/loginUser")
//                        .loginProcessingUrl("/users/login-user")
//                        .defaultSuccessUrl("/index.html", true)
//                .usernameParameter("email")
//                .passwordParameter("password");
//        http.authorizeRequests()
//                .antMatchers("/").permitAll().antMatchers("/index.html").hasAnyRole("USER", "ADMIN")
//                .and();

//        http.authorizeRequests().mvcMatchers("/users/login-user").permitAll()
//                .mvcMatchers("/products/cognac").permitAll();


//        http.authorizeRequests()
//                .mvcMatchers("/products").access("hasAnyAuthority('ADMIN')")
//                .mvcMatchers("/addUser").permitAll()
//                .mvcMatchers("/orderlist").permitAll()
//                .anyRequest().authenticated();



        // for adding users
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest().permitAll();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsPasswordService(
                passwordService);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
