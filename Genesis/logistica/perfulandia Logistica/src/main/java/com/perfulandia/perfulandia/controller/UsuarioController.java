package com.perfulandia.perfulandia.controller;

import com.perfulandia.perfulandia.services.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.perfulandia.model.Login;
import com.perfulandia.perfulandia.model.Usuario;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas a usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Crear Usuario
    @PostMapping
    @Operation(summary = "Crear un usuario", description = "Crea un usuario")
    public String crearUsuario(@RequestBody Usuario usuario){
        usuarioService.crearUsuario(usuario);
        return "Usuario creado";
    }

    //Listar todos los usuarios
    @GetMapping
    @Operation(summary = "Obtener una lista con todos los usuarios", description = "Obtiene una lista con todos los usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    //Obtener usuario por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario", description = "Obtiene un usuario")
    public Usuario obtenerUsuario(@PathVariable int id){
        return usuarioService.obtenerUsuario(id);
    }

    //Actualizar usuario
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza un usuario")
    public String actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        usuarioService.actualizarUsuario(id, usuario);
        return "Usuario actualizado";
    }

    //Desactivar usuario
    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar un usuario", description = "Desactiva un usuario")
    public String desactivarUsuario(@PathVariable int id){
        usuarioService.desactivarUsuario(id);
        return "Usuario desactivado";
    }

    //Eliminar Usuario
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario")
    public String eliminarUsuario(@PathVariable int id){
        usuarioService.eliminarUsuario(id);
        return "Usuario Eliminado";
    }

    @PostMapping("/login")
    @Operation(summary = "Crear un login", description = "Crea un login")
    public ResponseEntity<String> login(@RequestBody Login login){
        boolean exito = usuarioService.login(login.getCorreo(), login.getContrasena());
        if(exito){
            return ResponseEntity.ok("Login exitoso");
        }else{
            return ResponseEntity.status(401).body("Correo o contraseña incorrecta");
        }
    }
}