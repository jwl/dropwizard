package com.tutorial.app;

import com.tutorial.app.health.TemplateHealthCheck;
import com.tutorial.app.resources.HelloWorldResource;
import com.tutorial.app.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Server extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new Server().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    // This method is used to configure different aspects of the application,
    // as it gets executed before run(), like bundles, source providers, etc.
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource mainConfiguration = new HelloWorldResource(
            configuration.getTemplate(), configuration.getDefaultName());

        environment.jersey().register(mainConfiguration);
        environment.jersey().register(new UserResource());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        // nothing to do yet
    }
}