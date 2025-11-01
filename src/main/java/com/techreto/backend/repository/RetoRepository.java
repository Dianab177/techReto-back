package com.techreto.backend.repository;

import com.techreto.backend.model.Reto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RetoRepository extends JpaRepository<Reto, Long> {
    List<Reto> findByEstado(String estado);
}
