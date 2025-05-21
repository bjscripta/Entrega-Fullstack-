package com.venta.venta.controller;


import com.venta.venta.model.Inventario;
import com.venta.venta.model.Producto;
import com.venta.venta.repository.ProductoRepository;
import com.venta.venta.service.InventarioService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
public class InventarioController {

     @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<?> crearInventario(@RequestBody Inventario inventario) {
        try {
            if (inventario.getProducto() == null || inventario.getProducto().getIdProducto() == null) {
                return ResponseEntity.badRequest().body("ID de producto requerido");
            }

            Optional<Producto> productoOpt = productoRepository.findById(inventario.getProducto().getIdProducto());
            if (productoOpt.isEmpty()) {
                return ResponseEntity.status(404).body("Producto no encontrado");
            }

            inventario.setProducto(productoOpt.get());
            Inventario nuevo = inventarioService.guardar(inventario);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }
    @GetMapping
    public List<Inventario> listarInventario() {
        return inventarioService.listar();
    }

    @GetMapping("/{id}")
public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
    Optional<Inventario> inventarioOpt = inventarioService.obtenerPorId(id);

    if (inventarioOpt.isPresent()) {
        return ResponseEntity.ok(inventarioOpt.get());
    } else {
        return ResponseEntity.status(404).body("Inventario no encontrado");
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Inventario inventario) {
        Optional<Inventario> inventarioOpt = inventarioService.obtenerPorId(id);

        if (inventarioOpt.isPresent()) {
            Inventario inv = inventarioOpt.get();
            inv.setProducto(inventario.getProducto());
            inv.setStock(inventario.getStock());
            return ResponseEntity.ok(inventarioService.actualizar(inv));
        } else {
            return ResponseEntity.status(404).body("Inventario no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return inventarioService.eliminar(id)
                ? ResponseEntity.ok("Inventario eliminado")
                : ResponseEntity.status(404).body("Inventario no encontrado");
    }
}