package org.example.service1.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/service1")
public class ServiceS1Controller {
    private static final Logger log = LoggerFactory.getLogger(ServiceS1Controller.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/call")
    public String getClientId(@RequestHeader("sid") String sid, @RequestBody Map<String, String> clientId) {

        log.info("Received request with sid: {} and clientId: {}", sid, clientId);

        if (sid == null || !sid.equals("valid_sid")) {
            log.warn("Unauthorized request: sid is invalid or missing");
            return "Unauthorized";
        }


        Map<String, String> clientIdInfo = Map.of("clientId", clientId.get("clientId"));
        log.debug("Prepared clientIdInfo: {}", clientIdInfo);

        try {

            jmsTemplate.convertAndSend("clientId.queue", clientIdInfo);
            log.info("Client ID sent to JMS queue: {}", clientIdInfo);

            return "Client id has been sent.";
        } catch (Exception e) {
//
            log.error("Failed to send client ID to JMS queue: {}", e.getMessage(), e);
            return "Failed to send client ID.";
        }
    }
}
