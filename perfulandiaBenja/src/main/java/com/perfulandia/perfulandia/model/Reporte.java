package com.perfulandia.perfulandia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "reporte")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reporte {

    @Id//Se usa para que el id sea auto-incrementable
    private int idReporte;

    @Column(nullable = false, length = 50)//Es obligatorio con una longitud de 50 caracteres
    private String tituloReporte;

    @Column(nullable = false, length = 200)////Es obligatorio con una longitud de 200 caracteres
    private String descripcionReporte;

    @Column(nullable = false)
    private String estadoReporte;
    
}
