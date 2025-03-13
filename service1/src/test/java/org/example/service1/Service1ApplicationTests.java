package org.example.service1;

import org.example.service1.controller.ServiceS1Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class Service1ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private ServiceS1Controller controller;





    @Test
    void testGetClientId_Success() {
        doNothing().when(jmsTemplate).convertAndSend(anyString(), anyMap());
        String result = controller.getClientId("valid_sid", Map.of("clientId", "123"));
        assertEquals("Client id has been sent.", result);
    }

    @Test
    void testGetClientId_Unauthorized() {
        String result = controller.getClientId("invalid_sid", Map.of("clientId", "123"));
        assertEquals("Unauthorized", result);
    }

}
