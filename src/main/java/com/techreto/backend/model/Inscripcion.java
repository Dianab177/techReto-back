package com.techreto.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inscripcion")
@Data
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long idInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_reto", nullable = false)
    // 🔹 Cuando serializamos la Inscripcion, del Reto se ignora la propiedad "inscripciones"
    @JsonIgnoreProperties("inscripciones")
    private Reto reto;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @Column(name = "fecha_inscripcion", columnDefinition = "DATETIME")
    private LocalDateTime fechaInscripcion = LocalDateTime.now();

    @Column(name = "estado", length = 20)
    private String estado = "PENDIENTE";

    @Column(name = "oculto")
    private boolean oculto = false;
    @Column(length = 300)
    private String enlaceRepositorio;

    @Column(length = 300)
    private String enlaceFigma;

    @Column(length = 300)
    private String enlaceDemo;

    @Column(length = 20)
    private String estadoEntrega; // PENDIENTE - ENTREGADO

    @Column(length = 20)
    private String estadoAprobacion; // PENDIENTE - APROBADO - RECHAZADO


}
