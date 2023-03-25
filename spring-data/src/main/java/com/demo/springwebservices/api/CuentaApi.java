package com.demo.springwebservices.api;

import com.demo.springdata.dto.ClienteDto;
import com.demo.springdata.dto.CuentaDto;
import com.demo.springdata.service.ClienteService;
import com.demo.springdata.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cuenta")
@Slf4j
public class CuentaApi {

    @Autowired
    CuentaService cuentaService;

    @GetMapping("/{id}")
    public CuentaDto buscarCuenta(@PathVariable int id) {
        log.info("Busqueda de Cuenta por cliente_id: {}", id);
        return cuentaService.obtenerCuenta(id);
    }

    @GetMapping("/cliente/{id}")
    public List<CuentaDto> buscarCuentaporCliente(@PathVariable int id) {
        log.info("Busqueda de Cuenta por cliente_id: {}", id);
        return cuentaService.buscarCuentasPorCliente(id);
    }

    @PostMapping("/desactivar")
    public void desactivarCuenta(@Valid @RequestBody  CuentaDto cuentaDto) {
        log.info("Desactivar Cuenta: {}", cuentaDto);
        cuentaService.desactivarCuenta(cuentaDto);
    }

    @PostMapping
    public void guardarCuenta(@Valid @RequestBody CuentaDto cuentaDto) {
        log.info("Cuenta a Agregar: {}", cuentaDto);
        cuentaService.insertarCuenta(cuentaDto);
    }

    @PutMapping
    public void actualizarCuenta(@Valid @RequestBody CuentaDto cuentaDto) {
        log.info("Cuenta a Editar: {}", cuentaDto);
        cuentaService.actualizarCuenta(cuentaDto);
    }

}
