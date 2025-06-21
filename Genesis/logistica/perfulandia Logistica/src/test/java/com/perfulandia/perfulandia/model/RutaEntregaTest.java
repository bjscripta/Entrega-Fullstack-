package com.perfulandia.perfulandia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RutaEntregaTest {

    @Test
    public void testRutaEntregaGettersAndSetters() {
        RutaEntrega ruta = new RutaEntrega();
        ruta.setIdRuta(1);
        ruta.setOrigen("Rancagua");
        ruta.setDestino("La Serena");
        ruta.setDuracionEstimada(180);

        assertEquals(1, ruta.getIdRuta());
        assertEquals("Rancagua", ruta.getOrigen());
        assertEquals("La Serena", ruta.getDestino());
        assertEquals(180, ruta.getDuracionEstimada());
    }
}
