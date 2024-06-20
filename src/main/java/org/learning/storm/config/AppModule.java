package org.learning.storm.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.learning.storm.controller.EchoController;

@Singleton
public class AppModule extends AbstractModule {

    private final AppConfig appConfig;

    public AppModule(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    protected void configure() {
//        bind(EchoController.class).asEagerSingleton();
    }

    @Provides
    @javax.inject.Singleton
    public AppConfig getTemplate() {
        return this.appConfig;
    }

}
