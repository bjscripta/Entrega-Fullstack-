package com.venta.venta.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class VentaRequest {
    private Long idUsuario;
    private String descripcion;
    private LocalDate fechaVenta;
    private List<DetalleVentaDTO> detalles;
}