package com.techreto.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "MiembroEquipo")  // Respeta tu convención CamelCase
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    private String rol;

    public MiembroEquipo(Equipo equipo, Usuario usuario, String rol) {
        this.id = new MiembroEquipoId(equipo.getIdEquipo(), usuario.getIdUsuario());
        this.equipo = equipo;
        this.usuario = usuario;
        this.rol = rol;
    }
}
