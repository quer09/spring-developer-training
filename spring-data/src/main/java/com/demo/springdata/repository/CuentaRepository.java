package com.demo.springdata.repository;

import com.demo.springdata.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer>, JpaSpecificationExecutor<Cuenta> {

    void deleteALLByCliente_Id(int clienteId);

    List<Cuenta> findCuentaByCliente_Id(int clienteId);

    List<Cuenta> findCuentaByCliente_IdAndEstadoIsTrue(int clienteId);
}
