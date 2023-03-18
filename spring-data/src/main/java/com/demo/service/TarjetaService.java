package com.demo.service;

import com.demo.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    TarjetaRepository tarjetaRepository;
}
