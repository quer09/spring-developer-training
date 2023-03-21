package com.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDto {
    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String paisNacimiento;

    private List<DireccionDto> direccionDto;
}
