package com.demo.springbeans.dto;

import lombok.Data;

@Data
public class ClienteQueryDto {
    private String textoBusqueda;
    private ClienteQueryType tipoBusqueda;
}
