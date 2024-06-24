package org.learning.storm.service;

import org.learning.storm.request.CreateOrderRequestDto;
import org.learning.storm.response.OrderDetailsResponseDto;

public interface OrderService {
    String createOrder(CreateOrderRequestDto order);
    OrderDetailsResponseDto getOrderById(Long orderId);
    String updateStatusOfOrder(String orderId, String status);
}
