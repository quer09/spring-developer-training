package com.demo.springbeans;

import com.demo.springbeans.dto.ClienteQueryDto;
import com.demo.springbeans.dto.ClienteQueryType;
import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.model.Cliente;
import com.demo.springdata.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdministradorClientes {
    private ClienteRepository clienteRepository;

    private ClienteQueryType defaultClienteQueryType;

    //BMV - Inyección por setters
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // public AdministradorClientes(ClienteQueryType defaultClienteQueryType) {
    //     this.defaultClienteQueryType = defaultClienteQueryType;
    //  }

    //BMV inyección por constructor


    public AdministradorClientes(ClienteRepository clienteRepository, ClienteQueryType defaultClienteQueryType) {
        System.out.println("Inicializado contructor AdministracionClientes" + this);
        this.clienteRepository = clienteRepository;
        this.defaultClienteQueryType = defaultClienteQueryType;
    }


    /*
    public AdministradorClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

     */
    public List<ClienteDto> obtenerListaClientesPorCriterio(ClienteQueryDto clienteQueryDto) {
        List<Cliente> clientes = null;

        //Obtiene el valor (defaultClienteQueryType) por la lista de dependencia.
        if (clienteQueryDto.getTipoBusqueda() == null) {
            clienteQueryDto.setTipoBusqueda(defaultClienteQueryType);
        }

        if (ClienteQueryType.CEDULA.equals(clienteQueryDto.getTipoBusqueda())) {
            clientes = this.clienteRepository.findByCedula(clienteQueryDto.getTextoBusqueda());
        } else if (ClienteQueryType.NOMBRES.equals(clienteQueryDto.getTipoBusqueda())) {
            clientes = this.clienteRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(clienteQueryDto.getTextoBusqueda(), clienteQueryDto.getTextoBusqueda());
        }
        return Optional.ofNullable(clientes).map(clientesAux-> clientesAux.stream().map(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setTelefono(cliente.getTelefono());
            return clienteDto;
        }).collect(Collectors.toList())).orElse(new ArrayList<>());
    }



}
