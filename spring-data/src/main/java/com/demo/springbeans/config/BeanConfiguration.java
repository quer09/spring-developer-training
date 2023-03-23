package com.demo.springbeans.config;

import com.demo.springbeans.AdministradorClientes;
import com.demo.springbeans.dto.ClienteQueryType;
import com.demo.springdata.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Autowired
    private ClienteRepository clienteRepository;

    @Bean
    public AdministradorClientes administradorClientesBean() {
        //Puede tener lógica adicional si es necesario
        //Se devuelve la instancia del objeto que se desea exponer como Bean
        return new AdministradorClientes(clienteRepository, ClienteQueryType.CEDULA);
    }


}
