package com.perfulandia.perfulandia.repository;

import com.perfulandia.perfulandia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
