package org.learning.storm;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.learning.storm.config.AppConfig;
import org.learning.storm.config.AppModule;
import org.learning.storm.controller.EchoController;

public class OrderApplication extends Application<AppConfig> {

    private GuiceBundle<AppConfig> guiceBundle;
    public static void main(String[] args) throws Exception {
        new OrderApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {

//        this.guiceBundle = GuiceBundle.<AppConfig>newBuilder()
////                .addModule(module)
//                .addModule(new AppModule())
//                .enableAutoConfig(getClass().getPackage().getName())
//                .setConfigClass(AppConfig.class)
//                .build(Stage.DEVELOPMENT);
//
//        bootstrap.addBundle(guiceBundle);

    }
    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new AppModule(appConfig));
        environment.jersey().register(injector.getInstance(EchoController.class));
    }
}