package com.demo.springbeans.business;

import com.demo.springdata.dto.CuentaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuscadorCuentasTest {

    @Autowired
    BuscadorCuentas servicioCuentasBdd;

    @Autowired
    BuscadorCuentas servicioCuentasExterno;

    @Test
    void buscarCuentasPorClienteBdd() {
        List<CuentaDto> resultado = servicioCuentasBdd.buscarCuentasPorCliente(1);
        resultado.forEach(cuentaDto -> System.out.println("Cuenta de cliente: "+ cuentaDto));
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    void buscarCuentasPorClienteSistemaExterno() {
        List<CuentaDto> resultado = servicioCuentasExterno.buscarCuentasPorCliente(1);
        resultado.forEach(cuentaDto -> System.out.println("Cuenta de cliente: "+ cuentaDto));
        Assertions.assertEquals(1, resultado.size());
    }

}