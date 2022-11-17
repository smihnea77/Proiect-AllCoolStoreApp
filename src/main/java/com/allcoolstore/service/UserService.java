package com.allcoolstore.service;

import com.allcoolstore.model.User;
import com.allcoolstore.repository.UserRepository;
//import com.allcoolstore.security.adapter.UserDetailsAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
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
        validateEmail(user.getEmail());
        validateUsername(user.getUsername());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setAddress(user.getAddress());
        userRepository.save(userToUpdate);
    }

    private User validateEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if (userOptional.isPresent()) {
            throw new IllegalStateException(String.format("Email address %s already exists.", email));
        }return userRepository.findByEmail(email);
    }
    private User validateUsername(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            throw new IllegalStateException(String.format("Username %s already exists.", username));
        }return userRepository.findByUsername(username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
//        User foundUser = user.orElseThrow(() -> new UsernameNotFoundException("Error! Username not found"));
//        return new UserDetailsAdapter(foundUser);
//    }

}
