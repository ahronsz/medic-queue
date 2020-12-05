package com.upc.queue.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProductor {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("cola.medic")
    private String colaDestino;

    public void send(String mensaje) {
        jmsTemplate.convertAndSend(colaDestino, mensaje);
    }
}
