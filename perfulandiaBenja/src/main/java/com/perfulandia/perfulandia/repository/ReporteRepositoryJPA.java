package com.perfulandia.perfulandia.repository;

import com.perfulandia.perfulandia.model.Reporte;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReporteRepositoryJPA extends JpaRepository<Reporte, Integer> {

    Optional<Reporte> findById(int idReporte); 

    void deleteById(int idReporte);  
}
