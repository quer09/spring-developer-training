package com.demo.springdata.service;

import com.demo.springdata.criteria.CuentaSpecification;
import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.dto.CuentaDto;
import com.demo.springdata.model.Cliente;
import com.demo.springdata.model.Cuenta;
import com.demo.springdata.repository.ClienteRepository;
import com.demo.springdata.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    CuentaRepository cuentaRepository;
    ClienteRepository clienteRepository;
    CuentaSpecification cuentaSpefication;

    private CuentaDto fromClienteToDto(Cuenta cuenta){
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    public List<CuentaDto> buscarCuentasDinamicamentePorCriterio(CuentaDto cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpefication.buildFilter(cuentaDtoFilter))
                .stream().map(this::fromClienteToDto).collect(Collectors.toList());
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

}