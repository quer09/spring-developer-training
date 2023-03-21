package com.demo.service;

import com.demo.dto.TarjetaDto;
import com.demo.model.Tarjeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TarjetaServiceTest {

    @Autowired
    private TarjetaService tarjetaService;
    @Test
    void cambiarEstadoTarjetaPorId() {
        TarjetaDto tarjetaDto = tarjetaService.cambiarEstadoTarjetaPorId(1);
        System.out.println("Tarjeta Actualizada: " + tarjetaDto);
        assertEquals(false, tarjetaDto.getEstado());
    }
}