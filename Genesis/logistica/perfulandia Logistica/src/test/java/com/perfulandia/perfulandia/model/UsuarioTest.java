package com.perfulandia.perfulandia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.perfulandia.perfulandia.model.Usuario.EstadoUsuario;

public class UsuarioTest {

    @Test
    public void testUsuarioGettersAndSetters() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Genesis");
        usuario.setCorreo("genesis@correo.cl");
        usuario.setContrasena("1234");
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setPermisos(List.of("LECTURA", "ESCRITURA"));

        assertEquals(1, usuario.getId());
        assertEquals("Genesis", usuario.getNombre());
        assertEquals("genesis@correo.cl", usuario.getCorreo());
        assertEquals("1234", usuario.getContrasena());
        assertEquals(EstadoUsuario.ACTIVO, usuario.getEstado());
        assertEquals(2, usuario.getPermisos().size());
        assertTrue(usuario.getPermisos().contains("LECTURA"));
    }
}
