package com.perfulandia.perfulandia.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.perfulandia.perfulandia.model.RutaEntrega;

@DataJpaTest
public class RutaEntregaRepositoryTest {

    @Autowired
    private RutaEntregaRepository rutaEntregaRepository;

    @Test
    public void testGuardarRuta() {
        RutaEntrega ruta = new RutaEntrega();
        ruta.setOrigen("Concepcion");
        ruta.setDestino("Temuco");
        ruta.setDuracionEstimada(180);

        RutaEntrega guardada = rutaEntregaRepository.save(ruta);
        assertNotNull(guardada.getIdRuta());
        assertEquals("Temuco", guardada.getDestino());
    }
}
