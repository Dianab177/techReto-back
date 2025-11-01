package com.techreto.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Equipo")
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long idEquipo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;
}
