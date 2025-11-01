package com.techreto.backend.repository;

import com.techreto.backend.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    Valoracion findByEntrega_IdEntrega(Long idEntrega);
}
