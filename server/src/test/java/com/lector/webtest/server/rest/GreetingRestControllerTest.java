package com.lector.webtest.server.rest;

import com.lector.webtest.server.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/10/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Server.class)
public class GreetingRestControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testWithoutParameter() throws Exception {
        mockMvc.perform(get("/rest/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("Hello, World!"));
        //.andExpect(jsonPath("$.description", is("A description")));

    }

    @Test
    public void testWithParameter() throws Exception {
        mockMvc.perform(get("/rest/greeting?name=reza"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("Hello, reza!"));

    }

}
