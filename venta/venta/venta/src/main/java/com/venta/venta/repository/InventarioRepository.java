package com.venta.venta.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.venta.venta.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findByProducto_IdProducto(Long idProducto);

}
