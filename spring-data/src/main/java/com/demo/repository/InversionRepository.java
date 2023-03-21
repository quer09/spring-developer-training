package com.demo.repository;

import com.demo.model.Cuenta;
import com.demo.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InversionRepository extends JpaRepository<Inversion, Integer> {

    List<Inversion> findInversionByCliente_Id(int clienteId);
}
