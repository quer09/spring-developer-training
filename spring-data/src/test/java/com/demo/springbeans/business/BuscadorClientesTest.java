package com.demo.springbeans.business;

import com.demo.springbeans.dto.ClienteQueryDto;
import com.demo.springbeans.dto.ClienteQueryType;
import com.demo.springdata.dto.ClienteDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuscadorClientesTest {

    @Autowired
    private BuscadorClientes baseDeDatos;

    @Autowired
    @Qualifier("sistemaExterno")

    private BuscadorClientes buscadorClientes;

    @Test
    void obtenerListaClientesDeBaseDeDatos() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("1111");
        clienteQueryDto.setTipoBusqueda(ClienteQueryType.CEDULA);
        List<ClienteDto> resultadoClienteDtos = baseDeDatos.obtenerListaClientes(clienteQueryDto);
        resultadoClienteDtos.forEach(clienteDto ->
                {
                    System.out.println("Resultado de la busqueda" + clienteDto.getNombre() + clienteDto.getApellidos());
                }
        );
        Assertions.assertEquals(1, resultadoClienteDtos.size());
    }

    @Test
    void obtenerListaClientesDeSistemaExterno() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("1890000000");
        clienteQueryDto.setTipoBusqueda(ClienteQueryType.CEDULA);
        List<ClienteDto> resultadoClienteDtos = buscadorClientes.obtenerListaClientes(clienteQueryDto);
        resultadoClienteDtos.forEach(clienteDto ->
                {
                    System.out.println("Resultado de la busqueda" + clienteDto.getNombre() + clienteDto.getApellidos());
                }
        );
        Assertions.assertEquals(1, resultadoClienteDtos.size());
    }
}