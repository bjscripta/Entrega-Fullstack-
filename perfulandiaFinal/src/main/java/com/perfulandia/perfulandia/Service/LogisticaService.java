package com.perfulandia.perfulandia.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.model.Proveedor;
import com.perfulandia.perfulandia.model.RutaEntrega;
import com.perfulandia.perfulandia.repository.EnvioRepository;
import com.perfulandia.perfulandia.repository.ProveedorRepository;
import com.perfulandia.perfulandia.repository.RutaEntregaRepository;

@Service
public class LogisticaService {
    @Autowired private EnvioRepository envioRepository;
    @Autowired private ProveedorRepository proveedorRepository;
    @Autowired private RutaEntregaRepository rutaEntregaRepository;

    //Envios
    public List<Envio> obtenerTodosLosEnvios() {return envioRepository.findAll();}
    public Envio guardarEnvio(Envio envio) {
        Proveedor proveedor = proveedorRepository.findById(envio.getProveedor().getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        RutaEntrega ruta = rutaEntregaRepository.findById(envio.getRutaEntrega().getIdRuta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        envio.setProveedor(proveedor);
        envio.setRutaEntrega(ruta);

        return envioRepository.save(envio);
    }
    public void eliminarEnvio(int id) {envioRepository.deleteById(id);}

    //Proveedores
    public List<Proveedor> obtenerProveedores() {return proveedorRepository.findAll();}
    public Proveedor guardarProveedor(Proveedor proveedor) {return proveedorRepository.save(proveedor);}

    //Rutas
    public List<RutaEntrega> obtenerRutas() {return rutaEntregaRepository.findAll();}
    public RutaEntrega guardarRuta(RutaEntrega ruta) {return rutaEntregaRepository.save(ruta);}
}
