package com.lector.webtest.server;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/9/2016
 */
@SpringBootApplication
public class Server extends SpringBootServletInitializer {

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Server.class);
    }

    public static void main(String args[]) {
        logger.info("Running the application ...");
        final SpringApplication application = new SpringApplication(Server.class);
        application.run();
    }

}
