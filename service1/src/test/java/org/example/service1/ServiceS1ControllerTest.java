package org.example.service1;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service1.controller.ServiceS1Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;
import java.util.Queue;

@ExtendWith(MockitoExtension.class)
public class ServiceS1ControllerTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private Queue<Map<String, String>> messageQueue;

    @InjectMocks
    private ServiceS1Controller serviceS1Controller;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceS1Controller).build();
    }

    @Test
    public void testGetClientId_Unauthorized() throws Exception {
        mockMvc.perform(post("/service1/call")
                        .header("sid", "invalid_sid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("clientId", "123"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Unauthorized"));
    }

    @Test
    public void testGetClientId_Success() throws Exception {
        mockMvc.perform(post("/service1/call")
                        .header("sid", "valid_sid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("clientId", "123"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Client id has been sent."));

        verify(jmsTemplate).convertAndSend(eq("clientId.queue"), any(Map.class));
    }

}