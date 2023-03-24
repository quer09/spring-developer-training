package com.demo.springbeans.business.impl;

import com.demo.springbeans.business.BuscadorCuentas;
import com.demo.springdata.dto.CuentaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("servicioCuentasExterno")
public class BuscadorCuentasSistemaExterno implements BuscadorCuentas {

    @Override
    public List<CuentaDto> buscarCuentasPorCliente(int idCliente) {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setIdCliente(1);
        cuentaDto.setId(1);
        cuentaDto.setEstado(true);
        cuentaDto.setNumero("123123213231212");
        cuentaDto.setTipo("VISA");
        return List.of(cuentaDto);
    }
}
