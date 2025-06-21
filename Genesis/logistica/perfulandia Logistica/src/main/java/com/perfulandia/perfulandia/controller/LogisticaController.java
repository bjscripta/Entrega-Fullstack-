package com.perfulandia.perfulandia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.model.Proveedor;
import com.perfulandia.perfulandia.model.RutaEntrega;
import com.perfulandia.perfulandia.services.LogisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;;

@RestController
@RequestMapping("/logistica")
@Tag(name = "Logistica", description = "Operaciones relacionadas a logistica (envios, proveedores y rutas)")
public class LogisticaController {

    @Autowired private LogisticaService logisticaService;

    //Envios
    @GetMapping("/envios")
    @Operation(summary = "Obtener todos los envios", description = "Obtiene una lista con todos los envios")
    public List<Envio> listarEnvios() {return logisticaService.obtenerTodosLosEnvios();}

    @PostMapping("/envios")
    @Operation(summary = "Crear envio", description = "Crea un envio")
    public Envio crearEnvio(@RequestBody Envio envio) {return logisticaService.guardarEnvio(envio);}

    @DeleteMapping("/envios/{id}")
    @Operation(summary = "Eliminar envio", description = "Elimina un envio")
    public void eliminarEnvio(@PathVariable int id) {logisticaService.eliminarEnvio(id);}

    //Proveedores
    @GetMapping("/proveedores")
    @Operation(summary = "Obtener todos los proveedores", description = "Obtiene una lista con todos los proveedores")
    public List<Proveedor> listarProveedores() {return logisticaService.obtenerProveedores();}

    @PostMapping("/proveedores")
    @Operation(summary = "Crear proveedor", description = "Crea un proveedor")
    public Proveedor creaProveedor(@RequestBody Proveedor proveedor) {
        return logisticaService.guardarProveedor(proveedor);
    }

    //Rutas
    @GetMapping("/rutas")
    @Operation(summary = "Obtener todas las rutas", description = "Obtiene una lista con todas las rutas")
    public List<RutaEntrega> listarRutas() {return logisticaService.obtenerRutas();}

    @PostMapping("/rutas")
    @Operation(summary = "Crear ruta", description = "Crea una ruta")
    public RutaEntrega crearRuta(@RequestBody RutaEntrega ruta) {
        return logisticaService.guardarRuta(ruta);
    }
}
