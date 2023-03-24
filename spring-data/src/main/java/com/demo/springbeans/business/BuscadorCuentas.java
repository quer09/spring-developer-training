package com.demo.springbeans.business;

import com.demo.springdata.dto.CuentaDto;

import java.util.List;

public interface BuscadorCuentas {

    List<CuentaDto> buscarCuentasPorCliente(int idCliente);
}
