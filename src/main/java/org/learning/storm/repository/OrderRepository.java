package org.learning.storm.repository;

import org.learning.storm.entity.Orders;

public interface OrderRepository {
    Orders save(Orders order);
    Orders findById(Long id);
}
