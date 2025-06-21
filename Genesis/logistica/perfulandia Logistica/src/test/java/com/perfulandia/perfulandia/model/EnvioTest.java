package com.perfulandia.perfulandia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EnvioTest {

    @Test
    public void testEnvioSettersAndGetters() {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombre("Proveedor A");
        proveedor.setContacto("contacto@proveedor.com");

        RutaEntrega ruta = new RutaEntrega();
        ruta.setIdRuta(1);
        ruta.setOrigen("Santiago");
        ruta.setDestino("Valparaiso");
        ruta.setDuracionEstimada(120);

        Envio envio = new Envio();
        envio.setIdEnvio(10);
        envio.setProveedor(proveedor);
        envio.setRutaEntrega(ruta);
        envio.setEstado("EN TRANSITO");
        envio.setFechaEntregaEstimada("2025-07-01");

        assertEquals(10, envio.getIdEnvio());
        assertEquals("EN TRANSITO", envio.getEstado());
        assertEquals("2025-07-01", envio.getFechaEntregaEstimada());
        assertEquals("Proveedor A", envio.getProveedor().getNombre());
        assertEquals("Valparaiso", envio.getRutaEntrega().getDestino());
    }
}
