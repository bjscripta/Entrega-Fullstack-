package com.venta.venta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.venta.venta.model.DetalleVenta;
import com.venta.venta.model.Inventario;
import com.venta.venta.model.Venta;
import com.venta.venta.repository.DetalleVentaRepository;
import com.venta.venta.repository.InventarioRepository;
import com.venta.venta.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final InventarioRepository inventarioRepository;

    public void registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        venta.setDetalles(detalles);
        ventaRepository.save(venta);

        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(venta);

            Inventario inventario = inventarioRepository.findByProducto_IdProducto(detalle.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto"));

            if (inventario.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + detalle.getProducto().getNombre());
            }

            inventario.setStock(inventario.getStock() - detalle.getCantidad());
            inventarioRepository.save(inventario);
        }

        detalleVentaRepository.saveAll(detalles);
    }
    public List<Venta> listar() {
    return ventaRepository.findAll();
}

public Venta obtenerPorId(Long id) {
    return ventaRepository.findById(id).orElse(null);
}
}