package com.perfulandia.perfulandia.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.perfulandia.perfulandia.model.Proveedor;

@DataJpaTest
public class ProveedorRepositoryTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    public void testGuardarProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor XYZ");
        proveedor.setContacto("xyz@correo.com");

        Proveedor guardado = proveedorRepository.save(proveedor);
        assertNotNull(guardado.getIdProveedor());
        assertEquals("Proveedor XYZ", guardado.getNombre());
    }
}
