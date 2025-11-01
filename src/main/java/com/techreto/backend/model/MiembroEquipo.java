package com.techreto.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MiembroEquipo")
@Data
public class MiembroEquipo {

    @EmbeddedId
    private MiembroEquipoId id;

    @ManyToOne
    @MapsId("idEquipo")
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
