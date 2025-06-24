package com.perfulandia.perfulandia.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.perfulandia.model.Login;
import com.perfulandia.perfulandia.model.Usuario;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.perfulandia.perfulandia.services.UsuarioService UsuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setId(1L);;
        usuario.setNombre("Camila");
        usuario.setCorreo("cami@gmail.com");
        usuario.setContrasena("1234");
        usuario.setEstado(Usuario.EstadoUsuario.ACTIVO);
        usuario.setPermisos(List.of("LECTURA", "ESCRITURA"));
    }

    @Test
    public void testCrearUsuario() throws Exception{
        doNothing().when(UsuarioService).crearUsuario(any(Usuario.class));

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
            .andExpect(status().isOk())
            .andExpect(content().string("Usuario creado"));
    }

    @Test
    public void testListarUsuarios() throws Exception {
        when(UsuarioService.listarUsuarios()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuarios"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nombre").value("Camila"));
    }

    @Test
    public void testObtenerUsuarioPorId() throws Exception {
        when(UsuarioService.obtenerUsuario(1)).thenReturn(usuario);

        mockMvc.perform(get("/usuarios/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombre").value("Camila"));
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        doNothing().when(UsuarioService).actualizarUsuario(eq(1), any(Usuario.class));

        mockMvc.perform(put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
            .andExpect(status().isOk())
            .andExpect(content().string("Usuario actualizado"));
    }

    @Test
    public void testDesactivarUsuario() throws Exception {
        doNothing().when(UsuarioService).desactivarUsuario(1);

        mockMvc.perform(patch("/usuarios/1/desactivar"))
            .andExpect(status().isOk())
            .andExpect(content().string("Usuario desactivado"));
    }

    @Test
    public void testEliminarUsuario() throws Exception {
        doNothing().when(UsuarioService).eliminarUsuario(1);

        mockMvc.perform(delete("/usuarios/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Usuario Eliminado"));
    }

    @Test
    public void testLoginExitoso() throws Exception{
        Login login = new Login("cami@gmail,con", "1234");
        when(UsuarioService.login(login.getCorreo(), login.getContrasena())).thenReturn(true);

        mockMvc.perform(post("/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
            .andExpect(status().isOk())
            .andExpect(content().string("Login exitoso"));
    }

    @Test
    public void testLoginFallido() throws Exception{
        Login login = new Login("cami@gmail.com", "1234");
        when(UsuarioService.login(login.getCorreo(), login.getContrasena())).thenReturn(false);

        mockMvc.perform(post("/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string("Correo o contraseña incorrecta"));
    }
}
