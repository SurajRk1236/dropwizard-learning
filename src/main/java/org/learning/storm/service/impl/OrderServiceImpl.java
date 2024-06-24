package org.learning.storm.service.impl;

import com.google.inject.Singleton;
import org.learning.storm.entity.Orders;
import org.learning.storm.repository.OrderRepository;
import org.learning.storm.request.CreateOrderRequestDto;
import org.learning.storm.response.OrderDetailsResponseDto;
import org.learning.storm.service.OrderService;
import org.learning.storm.utils.TransactionalManager;
import org.learning.storm.utils.providers.ModelMapperProvider;

import javax.inject.Inject;

@Singleton
public class OrderServiceImpl implements OrderService {

    private final ModelMapperProvider modelMapper;
    private final OrderRepository orderRepository;
    private final TransactionalManager transactionalManager;

    @Inject
    public OrderServiceImpl(ModelMapperProvider modelMapper, OrderRepository orderRepository, TransactionalManager transactionalManager) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.transactionalManager = transactionalManager;
    }

    @Override
    public String createOrder(CreateOrderRequestDto order) {
        Orders orders = modelMapper.convertOrderRequestToEntity(order);
        //add validation for orders
        return transactionalManager.transactional(session -> String.valueOf(orderRepository.save(orders)));
    }

    @Override
    public OrderDetailsResponseDto getOrderById(Long orderId) {
        //add proper exceptional handling
        return modelMapper.convertOrderEntityToResponse(transactionalManager.transactional(session -> orderRepository.findById(orderId)));
    }

    @Override
    public String updateStatusOfOrder(String orderId, String status) {
        // fetch the current status for the order
        // add validation for processing sequence for this
        // update using parameter update
        return "";
    }
}
