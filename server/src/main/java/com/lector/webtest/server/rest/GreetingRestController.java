package com.lector.webtest.server.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/10/2016
 */
@RestController
public class GreetingRestController {

    @RequestMapping(value = "/rest/greeting", produces = "application/json")
    public String greet(
            @RequestParam(name = "name", defaultValue = "World")String name
    ){
        return "Hello, " + name + "!";
    }
}
