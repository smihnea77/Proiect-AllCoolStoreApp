package com.allcoolstore.service;

import com.allcoolstore.model.Order;

public interface IOrderService {
    void updateOrder(Long id, Order order);
}
