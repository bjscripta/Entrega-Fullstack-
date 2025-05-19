package com.perfulandia.perfulandia.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.model.Usuario;

@Service
public class UsuarioService {

    private List<Usuario> usuarios=new ArrayList<>();

    public void crearUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    public Usuario obtenerUsuario(int id){
        return usuarios.stream()
            .filter(u -> u.getId()==id)
            .findFirst()
            .orElse(null);
    }

    public void actualizarUsuario(int id, Usuario usuarioActualizado){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId()==id) {
                usuarios.set(i, usuarioActualizado);
                return;
            }
        }
    }

    public void desactivarUsuario(int id){
        usuarios.stream()
                .filter(u -> u.getId()==id)
                .findFirst()
                .ifPresent(u -> u.setEstado(Usuario.EstadoUsuario.DESACTIVADO));
    }

    public void eliminarUsuario(int id){
        usuarios.removeIf(u -> u.getId()==id);
    }

    public boolean login(String correo, String contrasena){
        return usuarios.stream()
            .anyMatch(u -> u.getCorreo().equals(correo)&& u.getContrasena().equals(contrasena)
                            && u.getEstado()==Usuario.EstadoUsuario.ACTIVO);
    }
}