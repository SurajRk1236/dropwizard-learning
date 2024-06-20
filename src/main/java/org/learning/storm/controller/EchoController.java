package org.learning.storm.controller;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import org.learning.storm.config.AppConfig;
import org.learning.storm.response.CommonResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/echo")
@Produces(MediaType.APPLICATION_JSON)
public class EchoController {

    private final AppConfig appConfig;

    @Inject
    public EchoController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GET
    @Timed
    public CommonResponse<String> echo() {
        return CommonResponse.<String>builder().data(appConfig.getTemplate()).build();
    }
}
