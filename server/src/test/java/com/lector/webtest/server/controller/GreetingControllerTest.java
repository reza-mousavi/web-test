package com.lector.webtest.server.controller;

import com.lector.webtest.server.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/10/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Server.class)
public class GreetingControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testWithoutParameter() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("name", is("World")));
    }

    @Test
    public void testWithParameter() throws Exception {
        mockMvc.perform(get("/greeting?name=reza"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("name", is("reza")));
    }
}
