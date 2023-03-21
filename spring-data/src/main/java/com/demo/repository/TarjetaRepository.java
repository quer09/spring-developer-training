package com.demo.repository;

import com.demo.model.Inversion;
import com.demo.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {

    List<Tarjeta> findTarjetaByCliente_Id(int clienteId);
}
