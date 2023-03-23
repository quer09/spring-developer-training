package com.demo.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String paisNacimiento;

    private List<DireccionDto> direccionsDto;
}
