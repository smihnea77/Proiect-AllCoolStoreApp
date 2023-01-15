package com.allcoolstore.service;

import com.allcoolstore.model.User;
import com.allcoolstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void getAllUsersTest() {
        //given
        //when
        List<User> actualUser = userService.getAllUsers();
        //then
        assertNotNull(actualUser);
    }

    @Test
    void getByUserIdTest() {
        //given
        Long givenUserId = Long.valueOf(10);
        User mockedUser = new User();
        //when
        when(userRepository.findById(givenUserId)).thenReturn(Optional.of(mockedUser));

        User actualUser = userService.getByUserId(givenUserId);
        //then
        assertNotNull(actualUser);
    }



}
