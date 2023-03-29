package com.demo.springdata.service;

import com.demo.springdata.repository.InversionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InversionService {
    private InversionRepository inversionRepository;
}
