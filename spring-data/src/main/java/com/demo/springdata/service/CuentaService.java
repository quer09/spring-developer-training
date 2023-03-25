package com.demo.springdata.service;

import com.demo.springdata.criteria.CuentaSpecification;
import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.dto.CuentaDto;
import com.demo.springdata.model.Cliente;
import com.demo.springdata.model.Cuenta;
import com.demo.springdata.repository.ClienteRepository;
import com.demo.springdata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CuentaService {
    CuentaRepository cuentaRepository;
    ClienteRepository clienteRepository;
    CuentaSpecification cuentaSpefication;

    private CuentaDto fromCuentaToDto(Cuenta cuenta){
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        cuentaDto.setIdCliente(cuenta.getCliente().getId());
        return cuentaDto;
    }

    public List<CuentaDto> buscarCuentasDinamicamentePorCriterio(CuentaDto cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpefication.buildFilter(cuentaDtoFilter))
                .stream().map(this::fromCuentaToDto).collect(Collectors.toList());
    }

    public List<CuentaDto> buscarCuentasPorCliente(int idCliente) {
        List<CuentaDto> cuentasPorCliente = new ArrayList<>();
        cuentaRepository.findCuentaByCliente_IdAndEstadoIsTrue(idCliente)
                .stream()
                .map(cuenta -> {
                    cuentasPorCliente.add(fromCuentaToDto(cuenta));
                    log.info("Cuenta de Cliente :{}", cuenta);
                    return cuenta;}
                ).collect(Collectors.toList());
        return cuentasPorCliente;
    }

    public CuentaDto obtenerCuenta(int idCuenta) {

        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("Cuenta No Existe");
                        });
        return fromCuentaToDto(cuenta);
    }

    public CuentaDto desactivarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findById(cuentaDto.getId())
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("Cuenta No Existe");
                        });
        cuenta.setEstado(false);
        cuentaRepository.save(cuenta);
        return fromCuentaToDto(cuenta);
    }

    public void insertarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setEstado(cuentaDto.getEstado());

        Cliente cliente = clienteRepository.findClienteById(1);

        cuenta.setCliente(cliente);

        cuentaRepository.save(cuenta);
    }

    public void actualizarCuenta(CuentaDto cuentaDto) {

        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDto.getId());
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setEstado(cuentaDto.getEstado());
        cuentaRepository.save(cuenta);

    }

}
