package org.example.service5.service;

import org.example.service5.entity.ClientInfo;
import org.example.service5.repository.ClientInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ListenerService5 {

    private static final Logger log = LoggerFactory.getLogger(ListenerService5.class);

    @Autowired
    private ClientInfoRepository repository;

    @JmsListener(destination = "clinetContacts.queue")
    public void getFinalInfo(Map<String, String> clientInfo) {
        log.info("Received clientInfo: {}", clientInfo);
        try {
            ClientInfo info = new ClientInfo();
            info.setId(Long.valueOf(clientInfo.get("clientId")));
            info.setPib(clientInfo.get("pib"));
            info.setAddress(clientInfo.get("address"));
            info.setContacts(clientInfo.get("contacts"));
            repository.save(info);
            log.info("Client info saved: {}", info);
        } catch (Exception e) {
            log.error("Failed to process clientInfo: {}", e.getMessage());
        }
    }
}
