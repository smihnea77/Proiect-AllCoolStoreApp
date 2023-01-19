package com.allcoolstore.service;

import com.allcoolstore.model.User;
import com.allcoolstore.repository.UserRepository;
//import com.allcoolstore.security.adapter.UserDetailsAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.allcoolstore.security.adapter.UserDetailsAdapter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void registerUser(User user) {
        validateEmail(user.getEmail());
        validateUsername(user.getUsername());
        userRepository.save(user);
    }

    public void createUser(User user) {
        validateEmail(user.getEmail());
        validateUsername(user.getUsername());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        boolean userExists = userRepository.existsById(id);
        if (!userExists) {
            throw new IllegalStateException(String.format("User with id %s does not exist.", id));
        }
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(String.format("User with id %s does not exist.", id)));

        userRepository.save(user);
    }

    private User validateEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if (userOptional.isPresent()) {
            throw new IllegalStateException(String.format("Email address %s already exists.", email));
        }
        return userRepository.findByEmail(email);
    }

    private User validateUsername(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            throw new IllegalStateException(String.format("Username %s already exists.", username));
        }
        return userRepository.findByUsername(username);
    }

//    public boolean verifyEmailAndPassword(String email, String password) {
//        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmailAndPassword(email,password));
//        if (!userOptional.isPresent()) {
//           return false;
//        }
//        return true;
//    }




    public User getByUserId(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        User foundUser = user.orElseThrow(() -> new UsernameNotFoundException("Error! Username not found"));
        return new UserDetailsAdapter(foundUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User getLoggedUser(){
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        }
        User user = findByUsername(username);
        return user;
    }


}
