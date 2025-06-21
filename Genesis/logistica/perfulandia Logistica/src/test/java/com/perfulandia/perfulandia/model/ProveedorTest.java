package com.perfulandia.perfulandia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProveedorTest {

    @Test
    public void testProveedorGettersAndSetters() {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombre("Proveedor B");
        proveedor.setContacto("contacto@b.cl");

        assertEquals(1, proveedor.getIdProveedor());
        assertEquals("Proveedor B", proveedor.getNombre());
        assertEquals("contacto@b.cl", proveedor.getContacto());
    }
}
