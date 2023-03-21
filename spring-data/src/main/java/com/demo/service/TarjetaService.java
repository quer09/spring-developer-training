package com.demo.service;

import com.demo.dto.ClienteDto;
import com.demo.dto.TarjetaDto;
import com.demo.model.Cliente;
import com.demo.model.Tarjeta;
import com.demo.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    TarjetaRepository tarjetaRepository;

    private TarjetaDto fromClienteToDto(Tarjeta tarjeta){
        TarjetaDto tarjetaDto = new TarjetaDto();
        BeanUtils.copyProperties(tarjeta, tarjetaDto);
        return tarjetaDto;
    }

    public TarjetaDto cambiarEstadoTarjetaPorId(int id) {
        Tarjeta tarjeta = tarjetaRepository.findById(id).orElseThrow(
                () -> { throw new RuntimeException("Tarjeta No Existe");
                });

        tarjeta.setEstado(!tarjeta.getEstado());

        return fromClienteToDto(tarjeta);
    }
}
