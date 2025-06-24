package com.perfulandia.perfulandia.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.perfulandia.perfulandia.model.Usuario;
import com.perfulandia.perfulandia.model.Usuario.EstadoUsuario;

@ActiveProfiles("test") 
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Test User");
        usuario.setCorreo("test@correo.com");
        usuario.setContrasena("1234");
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setPermisos(List.of("LECTURA", "ESCRITURA"));

        Usuario guardado = usuarioRepository.save(usuario);
        assertNotNull(guardado.getId());
        assertEquals("Test User", guardado.getNombre());
    }
}
