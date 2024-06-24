package org.learning.storm.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.hibernate.SessionFactory;
import org.learning.storm.repository.OrderRepository;
import org.learning.storm.repository.dao.OrdersDao;
import org.learning.storm.service.OrderService;
import org.learning.storm.service.impl.OrderServiceImpl;

@Singleton
public class AppModule extends AbstractModule {

    private final AppConfig appConfig;
    private final Provider<SessionFactory> sessionFactory;

    public AppModule(final AppConfig appConfig, final Provider<SessionFactory> sessionFactoryProvider) {
        this.appConfig = appConfig;
        this.sessionFactory = sessionFactoryProvider;
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toProvider(sessionFactory).in(javax.inject.Singleton.class);
        bind(OrderService.class).to(OrderServiceImpl.class).in(Singleton.class);
        bind(OrderRepository.class).to(OrdersDao.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public AppConfig getAppConfig() {
        return this.appConfig;
    }

}
