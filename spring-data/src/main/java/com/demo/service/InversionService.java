package com.demo.service;

import com.demo.repository.InversionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InversionService {
    InversionRepository inversionRepository;
}
