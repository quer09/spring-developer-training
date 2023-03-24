package com.demo.springdata.repository;

import com.demo.springdata.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InversionRepository extends JpaRepository<Inversion, Integer> {

    List<Inversion> findInversionByCliente_Id(int clienteId);

    List<Inversion> findInversionByCliente_IdAndEstadoIsTrue(int clienteId);

    void deleteALLByCliente_Id(int clienteId);
}
