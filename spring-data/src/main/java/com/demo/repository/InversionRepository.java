package com.demo.repository;

import com.demo.model.Inversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InversionRepository extends JpaRepository<Inversion, Integer> {
}
