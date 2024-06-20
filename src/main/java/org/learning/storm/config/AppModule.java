package org.learning.storm.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

@Singleton
public class AppModule extends AbstractModule {

    private final AppConfig appConfig;

    public AppModule(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public AppConfig getAppConfig() {
        return this.appConfig;
    }

}
