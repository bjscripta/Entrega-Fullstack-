package com.venta.venta.service;


import com.venta.venta.model.Inventario;
import com.venta.venta.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public boolean eliminar(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Inventario> obtenerPorIdProducto(Long idProducto) {
        return inventarioRepository.findByProducto_IdProducto(idProducto);
    }

    public Inventario actualizar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
}