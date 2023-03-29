package com.demo.springdata.service;

import com.demo.springdata.dto.TarjetaDto;
import com.demo.springdata.model.Tarjeta;
import com.demo.springdata.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    private TarjetaRepository tarjetaRepository;

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
