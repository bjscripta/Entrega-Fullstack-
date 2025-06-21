package com.perfulandia.perfulandia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    public void testLoginGettersAndSetters() {
        Login login = new Login();
        login.setCorreo("user@correo.com");
        login.setContrasena("pass123");

        assertEquals("user@correo.com", login.getCorreo());
        assertEquals("pass123", login.getContrasena());
    }
}
