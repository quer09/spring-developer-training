package com.demo.service;

import com.demo.dto.ClienteDto;
import com.demo.model.Cliente;
import com.demo.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {
    ClienteRepository clienteRepository;

    public void insertCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellido());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPais(clienteDto.getPais());
        clienteRepository.save(cliente);
    }

    public ClienteDto obtenerCliente(int idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(
                        () -> { throw new RuntimeException("Cliente No Existe");
                        });

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setApellido((cliente.getApellidos()));
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setPais(cliente.getPais());

        return clienteDto;
    }

    public void actualizarCliente(ClienteDto clienteDto) {

        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellido());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPais(clienteDto.getPais());
        clienteRepository.save(cliente);

    }

    public void eliminarCliente(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    public void listarClienteporPais() {

    }
}
