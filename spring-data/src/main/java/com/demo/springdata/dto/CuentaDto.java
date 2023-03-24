package com.demo.springdata.dto;

import lombok.Data;

@Data
public class CuentaDto {
    private int id;
    private int IdCliente;
    private String numero;
    private String tipo;

    private Boolean estado;
}
