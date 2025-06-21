package com.perfulandia.perfulandia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.perfulandia.perfulandia.model.Usuario;
import com.perfulandia.perfulandia.services.UsuarioService;

public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();

        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Camila");
        usuario.setCorreo("cami@gmail.com");
        usuario.setContrasena("1234");
        usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);
        usuario.setPermisos(List.of("LECTURA", "ESCRITURA"));

        usuarioService.crearUsuario(usuario);
    }

    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Camila", usuarios.get(0).getNombre());
    }

    @Test
    void testObtenerUsuarioPorId() {
        Usuario result = usuarioService.obtenerUsuario(1);
        assertNotNull(result);
        assertEquals("Camila", result.getNombre());
    }

    @Test
    void testActualizarUsuario() {
        Usuario actualizado = new Usuario(1, "Camila Actualizada", "cami@gmail.com", "5678",
                Usuario.EstadoUsuario.ACTIVO, List.of("LECTURA"));

        usuarioService.actualizarUsuario(1, actualizado);
        Usuario result = usuarioService.obtenerUsuario(1);
        assertEquals("Camila Actualizada", result.getNombre());
    }

    @Test
    void testDesactivarUsuario() {
        usuarioService.desactivarUsuario(1);
        Usuario result = usuarioService.obtenerUsuario(1);
        assertEquals(Usuario.EstadoUsuario.DESACTIVADO, result.getEstado());
    }

    @Test
    void testEliminarUsuario() {
        usuarioService.eliminarUsuario(1);
        Usuario result = usuarioService.obtenerUsuario(1);
        assertNull(result);
    }

    @Test
    void testLoginExitoso() {
        boolean loginCorrecto = usuarioService.login("cami@gmail.com", "1234");
        assertTrue(loginCorrecto);
    }

    @Test
    void testLoginFallidoCorreoIncorrecto() {
        boolean loginFallido = usuarioService.login("otro@gmail.com", "1234");
        assertFalse(loginFallido);
    }

    @Test
    void testLoginFallidoClaveIncorrecta() {
        boolean loginFallido = usuarioService.login("cami@gmail.com", "wrong");
        assertFalse(loginFallido);
    }

    @Test
    void testLoginUsuarioDesactivado() {
        usuarioService.desactivarUsuario(1);
        boolean loginFallido = usuarioService.login("cami@gmail.com", "1234");
        assertFalse(loginFallido);
    }
}
