package org.learning.storm.repository.dao;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;
import org.learning.storm.entity.Orders;
import org.learning.storm.repository.OrderRepository;

import javax.inject.Singleton;

@Singleton
public class OrdersDao extends AbstractDAO<Orders> implements OrderRepository {

    @Inject
    public OrdersDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Orders save(Orders order) {
        return persist(order);
    }

    @UnitOfWork
    @Override
    public Orders findById(Long id) {

        return get(id);
    }
}
