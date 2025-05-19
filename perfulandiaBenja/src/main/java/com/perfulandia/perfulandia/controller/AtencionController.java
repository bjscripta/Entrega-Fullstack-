package com.perfulandia.perfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.perfulandia.Service.AtencionService;
import com.perfulandia.perfulandia.model.Atencion;

@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    //Mostrar todas las Atenciones
    @GetMapping
    public ResponseEntity<List<Atencion>> listarTodasAtenciones(){
        List<Atencion> atenciones = atencionService.getAtenciones();
        if(atenciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atenciones);
    }

    //Agregar Atencion
    @PostMapping
    public ResponseEntity<Atencion> guardarAtencion(@RequestBody Atencion atencion){
        Atencion nuevaAtencion = atencionService.saveAtencion(atencion);
        return ResponseEntity.ok(nuevaAtencion);
    }

    //Eliminar Atencion
    @DeleteMapping("/{idAtencion}")
    public ResponseEntity<String> eliminarAtencion(@PathVariable int idAtencion){
        atencionService.deleteAtencion(idAtencion);
        return ResponseEntity.ok("Atencion Eliminada!");
    }

    //Buscar atencion por id
    @GetMapping("/{idAtencion}")
    public ResponseEntity<Atencion> obtenerAtencionPorId(@PathVariable int idAtencion) {
        Atencion atencion = atencionService.getAtencionById(idAtencion);
        if(atencion == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atencion);
    }

    //Actualizar estado de una atencion
    @PutMapping("/resolverAtencion/{id}")
    public Atencion marcarComoResuelta(@PathVariable int id) {
        return atencionService.actualizarEstadoAtencion(id);
    }

}
