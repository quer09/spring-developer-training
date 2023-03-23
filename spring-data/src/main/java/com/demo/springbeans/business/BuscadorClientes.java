package com.demo.springbeans.business;

import com.demo.springbeans.dto.ClienteQueryDto;
import com.demo.springdata.dto.ClienteDto;

import java.util.List;

public interface BuscadorClientes {
    List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto);
}
