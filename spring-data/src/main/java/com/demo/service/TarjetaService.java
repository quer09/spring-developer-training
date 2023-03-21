package com.demo.service;

import com.demo.model.Tarjeta;
import com.demo.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    TarjetaRepository tarjetaRepository;

    public Tarjeta cambiarEstadoTarjetaPorId(int id) {
        Tarjeta tarjeta = tarjetaRepository.findById(id).orElseThrow(
                () -> { throw new RuntimeException("Tarjeta No Existe");
                });

        tarjeta.setEstado(!tarjeta.getEstado());

        return tarjeta;
    }
}
