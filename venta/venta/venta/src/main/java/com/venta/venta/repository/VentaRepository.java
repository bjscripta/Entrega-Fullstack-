package com.venta.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venta.venta.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {}