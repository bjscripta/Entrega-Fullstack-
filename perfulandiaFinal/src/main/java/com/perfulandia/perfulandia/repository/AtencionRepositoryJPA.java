package com.perfulandia.perfulandia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfulandia.perfulandia.model.Atencion;

public interface AtencionRepositoryJPA extends JpaRepository <Atencion, Integer> {

    Optional<Atencion> findById(int idAtencion);

    void deleteById(int idAtencion);

}
