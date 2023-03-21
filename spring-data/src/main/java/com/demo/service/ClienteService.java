package com.demo.service;

import com.demo.criteria.ClienteSpecification;
import com.demo.dto.ClienteDto;
import com.demo.model.Cliente;
import com.demo.repository.ClienteRepository;
import com.demo.repository.CuentaRepository;
import com.demo.repository.DireccionRepository;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private CuentaRepository cuentaRepository;
    
    private ClienteSpecification clienteSpecification;

    private ClienteDto fromClienteToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    public void insertCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        clienteRepository.save(cliente);
    }

    public ClienteDto obtenerCliente(int idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(
                        () -> { throw new RuntimeException("Cliente No Existe");
                        });

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setApellidos((cliente.getApellidos()));
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());

        return clienteDto;
    }

    public void actualizarCliente(ClienteDto clienteDto) {

        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        clienteRepository.save(cliente);

    }

    public void eliminarCliente(int idCliente) {
        direccionRepository.deleteALLByCliente_Id(idCliente);
        cuentaRepository.deleteALLByCliente_Id(idCliente);
        clienteRepository.deleteById(idCliente);
    }

    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYCuentasActivas(String codigoISOPais){
        List<ClienteDto> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisNacimientoAndCuentas_EstadoIsTrue(codigoISOPais);
        clientes.forEach(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(cliente.getId());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }

    public List<Cliente> buscarClientesPorApellido(String apellidos){
        return clienteRepository.buscarPorApellidos(apellidos);
    }

    public List<ClienteDto> buscarClientesPorApellidoNativo(String apellidos){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Tuple> tuples = clienteRepository.buscarPorApellidosNativo(apellidos);
        tuples.forEach(tuple -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setApellidos((String) tuple.get("apellidos"));
            clienteDto.setNombre((String) tuple.get("nombre"));
            clienteDto.setCedula((String) tuple.get("cedula"));
            clienteDtos.add(clienteDto);
            System.out.println(tuple.get("apellidos"));
        });
        return clienteDtos;
    }

    public List<ClienteDto> bucarCLientesByTajetasInactivas(String paisNacimiento){
        List<ClienteDto> resultadoClientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClienteByPaisNacimientoIsNotAndTarjetas_EstadoIsFalse(paisNacimiento);
        clientes.forEach(cliente -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(cliente.getId());
            clienteDto.setApellidos(cliente.getApellidos());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setCedula(cliente.getCedula());
            clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
            resultadoClientesDto.add(clienteDto);
            System.out.println(clienteDto);
        });
        return resultadoClientesDto;
    }
    
    public List<ClienteDto> buscarClientesDinamicamentePorCriterio(ClienteDto clienteDtoFilter){
        return clienteRepository.findAll(clienteSpecification.buildFilter(clienteDtoFilter))
                .stream().map(this::fromClienteToDto).collect(Collectors.toList());
    }
}
