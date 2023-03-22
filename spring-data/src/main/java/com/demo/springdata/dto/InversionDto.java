package com.demo.springdata.dto;

import lombok.Data;

@Data
public class InversionDto {
    private int id;
    private String numero;
    private String tipo;
    private Boolean estado;
}
