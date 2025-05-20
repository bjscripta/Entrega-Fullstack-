package com.perfulandia.perfulandia.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.perfulandia.perfulandia.model.Usuario;

@Repository
public class UsuarioRepository {

    private List<Usuario> usuarios=new ArrayList<>();

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public List<Usuario> listaUsuarios(){
        return usuarios;
    }

    public Optional<Usuario> obtenerUsuario(int id){
        return usuarios.stream()
            .filter(u -> u.getId()==id)
            .findFirst();
    }

    public boolean actualizarUsuario(int id, Usuario usuarioActualizado){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarios.set(i, usuarioActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean desactivarUsuario(int id){
        Optional<Usuario> usuarioOpt = obtenerUsuario(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setEstado(Usuario.EstadoUsuario.DESACTIVADO);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(int id){
        return usuarios.removeIf(u ->u.getId()==id);
    }
}