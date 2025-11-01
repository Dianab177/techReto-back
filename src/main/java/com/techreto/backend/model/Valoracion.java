package com.techreto.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Valoracion")
@Data
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValoracion;

    @OneToOne
    @JoinColumn(name = "id_entrega", nullable = false)
    private Entrega entrega;

    @Column(nullable = false)
    private Integer puntuacion; // 1 a 5

    @Column(length = 500)
    private String comentario;
}
