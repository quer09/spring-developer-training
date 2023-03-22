package com.demo.springdata.repository;

import com.demo.springdata.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

    void deleteALLByCliente_Id(int clienteId);
}
