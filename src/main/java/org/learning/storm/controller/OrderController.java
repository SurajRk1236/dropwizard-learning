package org.learning.storm.controller;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import org.learning.storm.request.CreateOrderRequestDto;
import org.learning.storm.response.CommonResponse;
import org.learning.storm.service.OrderService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {
    private final OrderService orderService;

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @POST
    @Timed
//    @UnitOfWork  use this in case u want transaction
    public CommonResponse<String> createOrder(CreateOrderRequestDto createOrderRequestDto) {
        return CommonResponse.<String>builder().data(orderService.createOrder(createOrderRequestDto)).build();
    }
}
