package com.demo.springbeans.business.impl;

import com.demo.springbeans.business.BuscadorClientes;
import com.demo.springbeans.dto.ClienteQueryDto;
import com.demo.springbeans.dto.ClienteQueryType;
import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.model.Cliente;
import com.demo.springdata.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("baseDeDatos")
public class BascadorClientesBdd implements BuscadorClientes {
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
        List<Cliente> clientes = null;
        if (ClienteQueryType.CEDULA.equals(clienteQueryDto.getTipoBusqueda())) {
            clientes = this.clienteRepository.findByCedula(clienteQueryDto.getTextoBusqueda());
        } else if (ClienteQueryType.NOMBRES.equals(clienteQueryDto.getTipoBusqueda())) {
            clientes = this.clienteRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(clienteQueryDto.getTextoBusqueda(), clienteQueryDto.getTextoBusqueda());
        }
        return Optional.ofNullable(clientes).map(clientesAux -> clientesAux.stream().map(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setTelefono(cliente.getTelefono());
            return clienteDto;
        }).collect(Collectors.toList())).orElse(new ArrayList<>());
    }
}
