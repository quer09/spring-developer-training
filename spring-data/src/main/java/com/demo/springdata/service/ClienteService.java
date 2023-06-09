package com.demo.springdata.service;

import com.demo.springdata.criteria.ClienteSpecification;

import com.demo.springdata.model.Cliente;
import com.demo.springdata.model.Cuenta;
import com.demo.springdata.model.Inversion;
import com.demo.springdata.model.Tarjeta;

import com.demo.springdata.dto.*;
import com.demo.springdata.repository.*;
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
    private TarjetaRepository tarjetaRepository;
    private InversionRepository inversionRepository;

    private ClienteSpecification clienteSpecification;

    private ClienteDto fromClienteToDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    private CuentaDto fromCuentaToDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    private TarjetaDto fromTarjetaToDto(Tarjeta tarjeta) {
        TarjetaDto tarjetaDto = new TarjetaDto();
        BeanUtils.copyProperties(tarjeta, tarjetaDto);
        return tarjetaDto;
    }

    private InversionDto fromInversionToDto(Inversion inversion) {
        InversionDto inversionDto = new InversionDto();
        BeanUtils.copyProperties(inversion, inversionDto);
        return inversionDto;
    }

    public void insertarCliente(ClienteDto clienteDto) {
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
                        () -> {
                            throw new RuntimeException("Cliente No Existe");
                        });

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setApellidos((cliente.getApellidos()));
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setTelefono(cliente.getTelefono());
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
        inversionRepository.deleteALLByCliente_Id(idCliente);
        tarjetaRepository.deleteALLByCliente_Id(idCliente);
        clienteRepository.deleteById(idCliente);
    }

    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYCuentasActivas(String codigoISOPais) {
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

    public List<Cliente> buscarClientesPorApellido(String apellidos) {
        return clienteRepository.buscarPorApellidos(apellidos);
    }

    public List<ClienteDto> buscarClientesPorApellidoNativo(String apellidos) {
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

    public List<ClienteDto> bucarCLientesByTajetasInactivas(String paisNacimiento) {
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

    public List<ClienteDto> buscarClientesDinamicamentePorCriterio(ClienteDto clienteDtoFilter) {
        return clienteRepository.findAll(clienteSpecification.buildFilter(clienteDtoFilter))
                .stream().map(this::fromClienteToDto).collect(Collectors.toList());
    }

    public ProductoDto obtenerTodosProductosClientesPorId(int id) {
        ProductoDto productoDto = new ProductoDto();

        //Cuentas
        List<Cuenta> cuentas = cuentaRepository.findCuentaByCliente_IdAndEstadoIsTrue(id);
        List<CuentaDto> cuentaDtos = new ArrayList<>();

        cuentas.forEach(cuenta -> {
            cuentaDtos.add(fromCuentaToDto(cuenta));
        });

        productoDto.setCuentas(cuentaDtos);

        //Tarjetas
        List<Tarjeta> tarjetas = tarjetaRepository.findTarjetaByCliente_IdAndEstadoIsTrue(id);
        List<TarjetaDto> tarjetaDtos = new ArrayList<>();

        tarjetas.forEach(tarjeta -> {
            tarjetaDtos.add(fromTarjetaToDto(tarjeta));
        });

        productoDto.setTarjetas(tarjetaDtos);

        //Inversiones
        List<Inversion> inversiones = inversionRepository.findInversionByCliente_IdAndEstadoIsTrue(id);
        List<InversionDto> inversionDtos = new ArrayList<>();

        inversiones.forEach(inversion -> {
            inversionDtos.add(fromInversionToDto(inversion));
        });

        productoDto.setInversiones(inversionDtos);

        return productoDto;
    }

    public List<ClienteDto> obtenerClientes() {

        List<ClienteDto> clienteDto = clienteRepository.findAll().
                stream().map(this::fromClienteToDto).collect(Collectors.toList());

        return clienteDto;
    }

    /* public List<ClienteDto> obtenerClientes() { //You can return the list in the list
        return clienteRepository.findAll().
                stream().map(this::fromClienteToDto).collect(Collectors.toList());
    }*/

    public io.spring.guides.gs_producing_web_service.Cliente obtenerClienteParaSoap(int idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cliente no existe");
                });
        io.spring.guides.gs_producing_web_service.Cliente clienteWS = new io.spring.guides.gs_producing_web_service.Cliente();
        clienteWS.setId(cliente.getId());
        clienteWS.setApellidos(cliente.getApellidos());
        clienteWS.setNombre(cliente.getNombre());
        clienteWS.setCedula(cliente.getCedula());
        clienteWS.setTelefono(cliente.getTelefono());
        clienteWS.setPaisNacimiento(cliente.getPaisNacimiento());

        return  clienteWS;
    }
}
