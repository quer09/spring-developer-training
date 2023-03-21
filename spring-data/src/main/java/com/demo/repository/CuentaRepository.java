package com.demo.repository;

import com.demo.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    void deleteALLByCliente_Id(int clienteId);
}
