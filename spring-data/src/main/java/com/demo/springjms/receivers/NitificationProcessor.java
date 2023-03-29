package com.demo.springjms.receivers;

import com.demo.springjms.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NitificationProcessor {

    @JmsListener(destination = "smsReceiverJms")// va a representar el nombre de la cola donde se está escuchando
    public void processMessage(NotificationDto notificationDto){
        // Logica de envio de SMS
        log.info("sms info: {}", notificationDto);
    }
}
