package org.learning.storm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.learning.storm.config.AppConfig;
import org.learning.storm.config.AppModule;
import org.learning.storm.controller.EchoController;
import org.learning.storm.controller.OrderController;

public class OrderApplication extends Application<AppConfig> {

    private ScanningHibernateBundle<AppConfig> hibernateBundle;

    public static void main(String[] args) throws Exception {
        new OrderApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        this.hibernateBundle = new ScanningHibernateBundle<AppConfig>(getClass().getPackage().getName()) {
            @Override
            public DataSourceFactory getDataSourceFactory(final AppConfig appConfig) {
                return appConfig.getDataSource();
            }
        };
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new AppModule(appConfig, () -> hibernateBundle.getSessionFactory()));
        environment.jersey().register(injector.getInstance(EchoController.class));
        environment.jersey().register(injector.getInstance(OrderController.class));
    }
}