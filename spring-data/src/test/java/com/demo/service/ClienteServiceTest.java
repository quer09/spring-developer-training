package com.demo.service;

import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.dto.ProductoDto;
import com.demo.springdata.model.Cliente;
import com.demo.springdata.service.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @PersistenceContext
    private EntityManager entityManager;
    @Test
    void insertCliente() {
        List<Cliente> clienteList = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println("Listar antes de insertar: " + clienteList);
        System.out.println("lista cuantos tiene: " + clienteList.size());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("Hidalgo");
        clienteDto.setNombre("Quervin");
        clienteDto.setCedula("207889845123");
        clienteDto.setTelefono("01452368798");

        clienteService.insertarCliente(clienteDto);

        clienteList = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        assertFalse(clienteList.isEmpty());

        System.out.println("lista cuantos tiene: " + clienteList.size());
        assertEquals("207889845123", clienteList.get(5).getCedula());
    }

    @Test
    void obtenerCliente() {
        ClienteDto clienteDto = clienteService.obtenerCliente(1);
        System.out.println("El cliente es:" + clienteDto);
        assertEquals(1, clienteDto.getId());
    }

    @Test
    void actualizarCliente() {
        //INSERT INTO CLIENTE (APELLIDOS, CEDULA, NOMBRE, TELEFONO) VALUES ('PEREZ', '1', 'ROBERTO', '093939393');

        ClienteDto clienteDtoBase = clienteService.obtenerCliente(1);
        System.out.println(clienteDtoBase);

        clienteDtoBase.setNombre(clienteDtoBase.getNombre() + "TEST");
        clienteDtoBase.setCedula(clienteDtoBase.getCedula() + "TEST");
        clienteDtoBase.setTelefono(clienteDtoBase.getTelefono() + "TEST");
        clienteDtoBase.setApellidos(clienteDtoBase.getApellidos() + "TEST");
        clienteService.actualizarCliente(clienteDtoBase);

        ClienteDto clienteDtoBaseUpdated = clienteService.obtenerCliente(1);

        System.out.println(clienteDtoBaseUpdated);
        assertEquals("ROBERTOTEST", clienteDtoBaseUpdated.getNombre());
        assertEquals("PEREZTEST", clienteDtoBaseUpdated.getApellidos());

    }

    @Test
    void eliminarCliente() {
        ClienteDto clienteDtoBase = clienteService.obtenerCliente(1);
        assertEquals(1, clienteDtoBase.getId());

        clienteService.eliminarCliente(1);

        try {
            clienteService.obtenerCliente(1);
            fail("No debe llegar aca");
        } catch (RuntimeException e) {
            System.out.println("CLIENTE NO EXISTE: " + e.getMessage());
        }

    }

    @Test
    void obtenerClientesPorCodigoISOPaisYCuentasActivas() {
        List<ClienteDto> clientesDto = clienteService.obtenerClientesPorCodigoISOPaisYCuentasActivas("CR");
        clientesDto.forEach(clienteDto -> {System.out.println("Cuentas Activas" + clienteDto);});
        assertEquals(1, clientesDto.size());
    }

    @Test
    void buscarClientesPorApellido() {
        List<Cliente> cliente =  clienteService.buscarClientesPorApellido("PEREZ");
        assertFalse(cliente.isEmpty());
        assertEquals("PEREZ", cliente.get(0).getApellidos());
    }

    @Test
    void buscarClientesPorApellidoNativo() {
        List<ClienteDto> clienteDto =  clienteService.buscarClientesPorApellidoNativo("PEREZ");
        assertFalse(clienteDto.isEmpty());
        assertEquals("PEREZ", clienteDto.get(0).getApellidos());
    }

    @Test
    void bucarCLientesByTajetasInactivas() {
        List<ClienteDto> clienteDto =  clienteService.bucarCLientesByTajetasInactivas("CR");
        System.out.println("Cliente obtenido:" + clienteDto);
        assertFalse(clienteDto.isEmpty());
        assertNotEquals("CR", clienteDto.get(0).getPaisNacimiento());
    }

    @Test
    void buscarClientesDinamicamentePorCriterio() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        List<ClienteDto> resultadoCriteriosConDatosDTO = clienteService.buscarClientesDinamicamentePorCriterio(clienteDto);
        resultadoCriteriosConDatosDTO.forEach(clienteDtoResultado -> {
            System.out.println("ClienteDto es:" + clienteDtoResultado);
        });
        assertEquals(4, resultadoCriteriosConDatosDTO.size());
    }

    @Test
    void obtenerTodosProductosClientesPorId() {

        ProductoDto productoDto = clienteService.obtenerTodosProductosClientesPorId(1);

        System.out.println("Productos del Cliente:" + productoDto);

        assertEquals(1, productoDto.getCuentas().size());
    }
}