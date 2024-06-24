package org.learning.storm.utils.providers;

import com.google.inject.Singleton;
import org.learning.storm.entity.Orders;
import org.learning.storm.request.CreateOrderRequestDto;
import org.learning.storm.response.OrderDetailsResponseDto;
import org.modelmapper.ModelMapper;

import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class ModelMapperProvider {

    private final ModelMapper modelMapper;

    public ModelMapperProvider() {
        this.modelMapper = new ModelMapper();
    }

    public ModelMapper get() {
        return modelMapper;
    }

    public Orders convertOrderRequestToEntity(CreateOrderRequestDto order) {
        return modelMapper.map(order, Orders.class);
    }

    public OrderDetailsResponseDto convertOrderEntityToResponse(Orders order) {
        return modelMapper.map(order, OrderDetailsResponseDto.class);
    }
}
