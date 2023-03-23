package com.demo.springbeans.business.impl;

import com.demo.springbeans.business.BuscadorClientes;
import com.demo.springbeans.dto.ClienteQueryDto;
import com.demo.springbeans.dto.ClienteQueryType;
import com.demo.springdata.dto.ClienteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sistemaExterno")
public class BuscadorClientesSistemaExterno implements BuscadorClientes {
    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
        List<ClienteDto> resultadoClientes = List.of(
                new ClienteDto(1, "Alberto", "Salazar", "1890000000", "0999714563", "CR", null),
                new ClienteDto(2, "Rosa", "Salazar", "1890000001", "0983475616", "CR", null),
                new ClienteDto(3, "Alexis", "Vivanco", "1890000002", "0983475616", "CR", null),
                new ClienteDto(4, "Natalie", "Vivanco", "1890000003", "0983665616", "CR", null),
                new ClienteDto(5, "Ximena", "Silva", "1890000004", "0983475616", "CR", null),
                new ClienteDto(6, "Thalia", "Rodriguez", "1890000005", "0983475616", "CR", null),
                new ClienteDto(7, "Jonh", "Rodriguez", "1890000006", "0983475616", "CR", null),
                new ClienteDto(8, "Eduardo", "Guerra", "1890000007", "0983475616", "CR", null),
                new ClienteDto(9, "Juan", "Vaca", "1890000008", "0983475616", "CR", null),
                new ClienteDto(10, "Cristina", "Ortiz", "1890000009", "0983475616", "CR", null)
        );

        return resultadoClientes.stream().filter(filter ->
                        clienteQueryDto.getTipoBusqueda() == ClienteQueryType.NOMBRES ?
                                filter.getNombre().equals(clienteQueryDto.getTextoBusqueda())
                                : filter.getCedula().equals(clienteQueryDto.getTextoBusqueda()))
                .toList();

    }
}
