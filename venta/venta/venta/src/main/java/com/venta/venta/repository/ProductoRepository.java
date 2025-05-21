package com.venta.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venta.venta.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
