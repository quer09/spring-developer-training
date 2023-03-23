package com.demo.springbeans;

import com.demo.springbeans.dto.ClienteQueryDto;
import com.demo.springbeans.dto.ClienteQueryType;
import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.repository.ClienteRepository;
import com.demo.springdata.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdministradorClientesScopeTest {

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes singleton1;

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes singleton2;

    @Autowired
    @Qualifier("defaultNombresPrototype")
    private AdministradorClientes prototype1;

    @Autowired
    @Qualifier("defaultNombresPrototype")
    private AdministradorClientes prototype2;

    @Autowired
    @Qualifier("defaultNombresPrototype")
    private AdministradorClientes prototype3;

    @Autowired
    @Qualifier("defaultNombresRequest")
    private AdministradorClientes request1;

    @Autowired
    @Qualifier("defaultNombresSession")
    private AdministradorClientes session1;

    @Autowired
    @Qualifier("defaultNombresApplication")
    private AdministradorClientes application1;

    @Test
    void instancias() {
        System.out.println("singleton1 " + singleton1);
        System.out.println("singleton2 " + singleton2);
        System.out.println("prototype1 " + prototype1);
        System.out.println("prototype2 " + prototype2);
        System.out.println("prototype3 " + prototype3);

        System.out.println("request1" + request1);
        System.out.println("session1" + session1);
        System.out.println("application1" + application1);

        Assertions.assertEquals(1, 1);
    }

}