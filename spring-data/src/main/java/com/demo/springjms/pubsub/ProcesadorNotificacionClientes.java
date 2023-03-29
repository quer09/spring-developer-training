package com.demo.springjms.pubsub;

import com.demo.springdata.dto.CuentaDto;
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
    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaEnvioFormatoClientes(Message<CuentaDto> message){
        CuentaDto cuentaDto = message.getPayload();
        log.info("consumirMensajeParaEnvioFormatoClientes");
        // Puede tener la logica que necesitemos
        // Envio SMS con formato a Cliente
        return MessageBuilder.withPayload("Mensaje recibido pór ProcesadorNotificacionClientes")
                .build();
    }
}
