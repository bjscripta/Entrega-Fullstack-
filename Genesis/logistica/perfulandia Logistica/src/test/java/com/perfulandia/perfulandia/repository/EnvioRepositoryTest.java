package com.perfulandia.perfulandia.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.perfulandia.perfulandia.model.Envio;
import com.perfulandia.perfulandia.model.Proveedor;
import com.perfulandia.perfulandia.model.RutaEntrega;

@DataJpaTest
public class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private RutaEntregaRepository rutaEntregaRepository;

    @Test
    public void testGuardarYBuscarEnvio() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor Test");
        proveedor.setContacto("contacto@correo.com");
        proveedor = proveedorRepository.save(proveedor);

        RutaEntrega ruta = new RutaEntrega();
        ruta.setOrigen("A");
        ruta.setDestino("B");
        ruta.setDuracionEstimada(90);
        ruta = rutaEntregaRepository.save(ruta);

        Envio envio = new Envio();
        envio.setProveedor(proveedor);
        envio.setRutaEntrega(ruta);
        envio.setEstado("PENDIENTE");
        envio.setFechaEntregaEstimada("2025-07-01");

        Envio guardado = envioRepository.save(envio);
        assertNotNull(guardado.getIdEnvio());
        assertEquals("PENDIENTE", guardado.getEstado());
    }
}
