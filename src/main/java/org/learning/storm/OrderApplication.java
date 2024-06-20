package org.learning.storm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.learning.storm.config.AppConfig;
import org.learning.storm.config.AppModule;
import org.learning.storm.controller.EchoController;

public class OrderApplication extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new OrderApplication().run(args);
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new AppModule(appConfig));
        environment.jersey().register(injector.getInstance(EchoController.class));
    }
}