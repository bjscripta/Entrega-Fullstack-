package com.perfulandia.perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    
    @Id
    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;
}
