package org.example.service3.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ListenerService3 {

    private static final Logger log = LoggerFactory.getLogger(ListenerService3.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "clientPib.queue")
    public void listenClientId(Map<String, String> clientInfo) {
        log.info("Received clientInfo: {}", clientInfo);
        clientInfo.put("address", "Lypkivskogo 50");
        jmsTemplate.convertAndSend("clinetAddress.queue", clientInfo);
        log.info("Sent clientInfo to clinetAddress.queue: {}", clientInfo);
    }
}
