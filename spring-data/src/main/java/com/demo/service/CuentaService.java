package com.demo.service;

import com.demo.criteria.CuentaSpecification;
import com.demo.dto.ClienteDto;
import com.demo.dto.CuentaDto;
import com.demo.model.Cliente;
import com.demo.model.Cuenta;
import com.demo.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    CuentaRepository cuentaRepository;
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

}
