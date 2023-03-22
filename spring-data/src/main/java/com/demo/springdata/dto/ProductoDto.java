package com.demo.springdata.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductoDto {
    List<CuentaDto> cuentas;
    List<TarjetaDto> tarjetas;
    List<InversionDto> inversiones;
}
