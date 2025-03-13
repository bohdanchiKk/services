package org.example.service4.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ListenerService4 {

    private static final Logger log = LoggerFactory.getLogger(ListenerService4.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "clinetAddress.queue")
    public void listenClientId(Map<String, String> clientInfo) {
        log.info("Received clientInfo: {}", clientInfo);
        clientInfo.put("contacts", "+380637249783, +380994098143");
        jmsTemplate.convertAndSend("clinetContacts.queue", clientInfo);
        log.info("Sent clientInfo to clinetContacts.queue: {}", clientInfo);
    }
}


