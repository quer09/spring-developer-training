package com.demo.dto;

import com.demo.model.Cuenta;
import com.demo.model.Direccion;
import com.demo.model.Inversion;
import com.demo.model.Tarjeta;
import lombok.Data;

import java.util.List;

@Data
public class ProductoDto {
    List<CuentaDto> cuentas;
    List<TarjetaDto> tarjetas;
    List<InversionDto> inversiones;
}
