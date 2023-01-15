package com.allcoolstore.service;

import com.allcoolstore.model.Order;
import com.allcoolstore.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Test
    void getAllOrdersTest() {
        //given
        //when
        List<Order> actualOrder = orderService.getAllOrders();
        //then
        assertNotNull(actualOrder);
    }


    @Test
    void getOrderById() {
        //given
        Long givenOrderId = Long.valueOf(10);
        Order mockedOrder = new Order();
        //when
        when(orderRepository.findById(givenOrderId)).thenReturn(Optional.of(mockedOrder));

        Order actualOrder = orderService.getOrderById(givenOrderId);
        //then
        assertNotNull(actualOrder);
    }
}
