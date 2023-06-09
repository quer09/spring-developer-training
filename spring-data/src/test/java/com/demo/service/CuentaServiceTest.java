package com.demo.service;

import com.demo.springdata.dto.CuentaDto;
import com.demo.springdata.service.CuentaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CuentaServiceTest {

    @Autowired
    private CuentaService cuentaService;
    @Test
    void buscarCuentasDinamicamentePorCriterio() {
        CuentaDto cuentaDto = new CuentaDto();
        //cuentaDto.setTipo("AHORROS");
        cuentaDto.setEstado(true);
        cuentaService.buscarCuentasDinamicamentePorCriterio(cuentaDto).forEach(
                cuentaDtoResultado -> {
                    System.out.println("Cuenta Resultado" + cuentaDtoResultado);
                    assertEquals(1, 1);
                }
        );
    }

    @Test
    void insertarCuenta() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setNumero("123456");
        cuentaDto.setTipo("AHORROS");
        cuentaDto.setEstado(true);

        cuentaService.insertarCuenta(cuentaDto);
    }
}