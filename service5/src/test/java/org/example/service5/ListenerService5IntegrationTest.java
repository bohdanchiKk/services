package org.example.service5;
import static org.mockito.Mockito.*;

import org.example.service5.entity.ClientInfo;
import org.example.service5.repository.ClientInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ListenerService5IntegrationTest {
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    @InjectMocks
//    private ClientInfoRepository repository;
//
//    @Test
//    public void testGetFinalInfo_Success() {
//        Map<String, String> clientInfo = Map.of(
//                "clientId", "123",
//                "pib", "Dmytro Petro Anton",
//                "address", "123 Street",
//                "contacts", "+380000"
//        );
//
//        jmsTemplate.convertAndSend("clinetContacts.queue", clientInfo);
//
//        verify(repository, timeout(5000)).save(any(ClientInfo.class));
//    }
//
//    @Test
//    public void testGetFinalInfo_Exception() {
//        Map<String, String> clientInfo = Map.of(
//                "clientId", "123",
//                "pib", "Dmytro Petro Anton",
//                "address", "123 Street",
//                "contacts", "+380000"
//        );
//
//
//        doThrow(new RuntimeException("Database error")).when(repository).save(any(ClientInfo.class));
//
//
//        jmsTemplate.convertAndSend("clinetContacts.queue", clientInfo);
//
//        verify(repository, timeout(5000)).save(any(ClientInfo.class));
//    }
}