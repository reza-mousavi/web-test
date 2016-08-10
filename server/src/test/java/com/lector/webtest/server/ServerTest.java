package com.lector.webtest.server;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/10/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Server.class)
public class ServerTest {

        @Test
        public void testHello(){
                Assert.assertEquals("1", "1");
        }

}
