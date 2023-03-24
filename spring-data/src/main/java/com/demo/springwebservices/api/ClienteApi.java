package com.demo.springwebservices.api;

import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cliente")
@Slf4j
public class ClienteApi {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto buscarCliente(@PathVariable int id) {
        log.info("Busqueda de Cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ClienteDto buscarClienteXML(@PathVariable int id) {
        log.info("Busqueda de Cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/xml/json/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ClienteDto buscarClienteXMLJSON(@PathVariable int id) {
        log.info("Busqueda de Cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping(value = "/parameter", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ClienteDto buscarClienteParametro(@RequestParam int id) {
        log.info("Busqueda de Cliente: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @PostMapping
    public void guardarCliente(@Valid @RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Agregar: {}", clienteDto);
        clienteService.insertarCliente(clienteDto);
    }

   /* @PostMapping(value = "/guardarClienteJSON")  //You can set a specific name in a Post
    public void guardarClienteJ(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Agregar: {}", clienteDto);
        clienteService.insertarCliente(clienteDto);
    }*/

    @GetMapping("/all")
    public List<ClienteDto> buscarCliente() {
        log.info("Busqueda de todos los Cliente");
        return clienteService.obtenerClientes();
    }

    @PutMapping
    public void actualizarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Editar: {}", clienteDto);
        clienteService.actualizarCliente(clienteDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable int id) {
        log.info("Id de Cliente a eliminar: {}", id);
        clienteService.eliminarCliente(id);
    }

}
