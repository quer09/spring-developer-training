package com.demo.springjms.pubsub;

import com.demo.springdata.dto.CuentaDto;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionClientes {

    private final static String TWILIO_ACCOUNT_SID = "";
    private final static String TWILIO_AUTH_TOKEN = "";
    private final static String TWILIO_MESSAGE_SID = "";

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaEnvioFormatoClientes(Message<CuentaDto> message){
        CuentaDto cuentaDto = message.getPayload();
        log.info("consumirMensajeParaEnvioFormatoClientes");
        // Puede tener la logica que necesitemos
        String sms = "Hola desde Twilio SMS";
        Twilio.init(TWILIO_ACCOUNT_SID.trim(), TWILIO_AUTH_TOKEN.trim());
        com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+593999714503"),
                TWILIO_MESSAGE_SID.trim(), sms).create();

        // Puede tener la logica que necesitemos
        // Envio SMS con formato a Cliente
        return MessageBuilder.withPayload("Mensaje recibido pór ProcesadorNotificacionClientes")
                .build();
    }
}
