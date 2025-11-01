package com.techreto.backend.repository;

import com.techreto.backend.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByInscripcion_IdInscripcion(Long idInscripcion);
}
