package com.demo.service;

import com.demo.model.Tarjeta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TarjetaServiceTest {

    @Autowired
    private TarjetaService tarjetaService;
    @Test
    void cambiarEstadoTarjetaPorId() {
        Tarjeta tarjeta = tarjetaService.cambiarEstadoTarjetaPorId(1);
        System.out.println("Tarjeta: " + tarjeta);
        assertEquals(false, tarjeta.getEstado());
    }
}