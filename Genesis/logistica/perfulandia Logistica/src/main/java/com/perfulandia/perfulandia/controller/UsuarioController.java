package com.perfulandia.perfulandia.controller;

import com.perfulandia.perfulandia.services.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.perfulandia.model.Login;
import com.perfulandia.perfulandia.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Crear Usuario
    @PostMapping
    public String crearUsuario(@RequestBody Usuario usuario){
        usuarioService.crearUsuario(usuario);
        return "Usuario creado";
    }

    //Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    //Obtener usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable int id){
        return usuarioService.obtenerUsuario(id);
    }

    //Actualizar usuario
    @PutMapping("/{id}")
    public String actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        usuarioService.actualizarUsuario(id, usuario);
        return "Usuario actualizado";
    }

    //Desactivar usuario
    @PatchMapping("/{id}/desactivar")
    public String desactivarUsuario(@PathVariable int id){
        usuarioService.desactivarUsuario(id);
        return "Usuario desactivado";
    }

    //Eliminar Usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id){
        usuarioService.eliminarUsuario(id);
        return "Usuario Eliminado";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        boolean exito = usuarioService.login(login.getCorreo(), login.getContrasena());
        if(exito){
            return ResponseEntity.ok("Login exitoso");
        }else{
            return ResponseEntity.status(401).body("Correo o contraseña incorrecta");
        }
    }
}