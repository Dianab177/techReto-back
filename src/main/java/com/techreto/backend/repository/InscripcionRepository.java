package com.techreto.backend.repository;

import com.techreto.backend.model.Inscripcion;
import com.techreto.backend.model.Reto;
import com.techreto.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByUsuario_IdUsuario(Long idUsuario);
    List<Inscripcion> findByReto_IdReto(Long idReto);
    List<Inscripcion> findByEquipo_IdEquipo(Long idEquipo);
    Optional<Inscripcion> findByUsuarioAndReto(Usuario usuario, Reto reto);
}
