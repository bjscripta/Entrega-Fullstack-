package com.venta.venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venta.venta.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
