package com.techreto.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroEquipoId implements Serializable {

    @Column(name = "id_equipo")
    private Long idEquipo;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiembroEquipoId)) return false;
        MiembroEquipoId that = (MiembroEquipoId) o;
        return idEquipo.equals(that.idEquipo) && idUsuario.equals(that.idUsuario);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idEquipo, idUsuario);
    }
}
