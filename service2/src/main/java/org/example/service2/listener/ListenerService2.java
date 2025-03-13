package org.example.service2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ListenerService2 {

    private static final Logger log = LoggerFactory.getLogger(ListenerService2.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "clientId.queue")
    public void listenClientId(Map<String, String> clientId) {
        log.info("Received clientId: {}", clientId);
        Map<String, String> clientInfo = Map.of(
                "clientId", clientId.get("clientId"),
                "pib", "Bohdan Ryzhko Romanovich"
        );
        jmsTemplate.convertAndSend("clientPib.queue", clientInfo);
        log.info("Sent clientInfo to clientPib.queue: {}", clientInfo);
    }
}
