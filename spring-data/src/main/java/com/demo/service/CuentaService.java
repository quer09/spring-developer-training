package com.demo.service;

import com.demo.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CuentaService {
    CuentaRepository cuentaRepository;
}
