package com.venta.venta.controller;

import com.venta.venta.dto.DetalleVentaDTO;
import com.venta.venta.dto.VentaRequest;
import com.venta.venta.model.DetalleVenta;
import com.venta.venta.model.Producto;
import com.venta.venta.model.Usuario;
import com.venta.venta.model.Venta;
import com.venta.venta.repository.ProductoRepository;
import com.venta.venta.repository.UsuarioRepository;
import com.venta.venta.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody VentaRequest request) {
        try {
            Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Venta venta = new Venta();
            venta.setUsuario(usuario);
            venta.setDescripcion(request.getDescripcion());
            venta.setFechaVenta(request.getFechaVenta());

            List<DetalleVenta> detalles = new ArrayList<>();
            for (DetalleVentaDTO dto : request.getDetalles()) {
                Producto producto = productoRepository.findById(dto.getIdProducto())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado: ID " + dto.getIdProducto()));

                DetalleVenta detalle = new DetalleVenta();
                detalle.setProducto(producto);
                detalle.setCantidad(dto.getCantidad());
                detalle.setVenta(venta); // ¡Importante si hay relación bidireccional!
                detalles.add(detalle);
            }

            ventaService.registrarVenta(venta, detalles);

            return ResponseEntity.ok("Venta registrada con éxito");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.listar();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaService.obtenerPorId(id);
        if (venta == null) {
            return ResponseEntity.status(404).body("Venta no encontrada");
        }
        return ResponseEntity.ok(venta);
    }
}
