package com.demo.springdata.dto;

import lombok.Data;

@Data
public class CuentaDto {
    private int id;
    private int idCliente;
    private String numero;
    private String tipo;

    private Boolean estado;
}
