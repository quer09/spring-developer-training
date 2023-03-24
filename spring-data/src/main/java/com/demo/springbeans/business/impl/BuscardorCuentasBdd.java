package com.demo.springbeans.business.impl;

import com.demo.springbeans.business.BuscadorCuentas;
import com.demo.springdata.dto.CuentaDto;
import com.demo.springdata.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("servicioCuentasBdd")
public class BuscardorCuentasBdd implements BuscadorCuentas {
    @Autowired
    private CuentaService cuentaService;

    @Override
    public List<CuentaDto> buscarCuentasPorCliente(int idCliente) {
        return cuentaService.buscarCuentasPorCliente(idCliente);
    }


}
