package com.perfulandia.perfulandia.controller;

import com.perfulandia.perfulandia.Service.ReporteService;
import com.perfulandia.perfulandia.model.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Mostrar todos los reportes
    @GetMapping
    public ResponseEntity<List<Reporte>> listarTodos() {
        List<Reporte> reportes = reporteService.getReportes();
        if(reportes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

     //Agregar reporte
     @PostMapping
     public ResponseEntity<Reporte> guardarReporte(@RequestBody Reporte reporte) {
         Reporte nuevoReporte = reporteService.saveReporte(reporte);
         return ResponseEntity.ok(nuevoReporte);
     }

     //Eliminar reporte
    @DeleteMapping("/{idReporte}")
    public ResponseEntity<String> eliminarReporte(@PathVariable int idReporte) {
        reporteService.deleteReporte(idReporte);
        return ResponseEntity.ok("Reporte eliminado!");
    }

    //Buscar reporte por id
    @GetMapping("/{idReporte}")
    public ResponseEntity<Reporte> obtenerReportePorId(@PathVariable int idReporte) {
        Reporte reporte = reporteService.getReporteById(idReporte);
        if(reporte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reporte);
    }

    //Actualizar estado de reporte
    @PutMapping("/resolverReporte/{idReporte}")
    public ResponseEntity<Reporte> actualizarEstadoReporte(@PathVariable int idReporte) {
        Reporte reporteActualizado = reporteService.actualizarEstadoReporte(idReporte);
        return ResponseEntity.ok(reporteActualizado);
    }


}
