package com.techreto.backend.repository;

import com.techreto.backend.model.Inscripcion;
import com.techreto.backend.model.Reto;
import com.techreto.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByUsuario_IdUsuario(Long idUsuario);

    List<Inscripcion> findByReto_IdReto(Long idReto);

    Optional<Inscripcion> findByUsuarioAndReto(Usuario usuario, Reto reto);

    List<Inscripcion> findByUsuario_IdUsuarioAndOcultoFalse(Long idUsuario);
}
