package com.techreto.backend.repository;

import com.techreto.backend.model.Reto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RetoRepository extends JpaRepository<Reto, Long> {

    List<Reto> findByEstado(String estado);

    List<Reto> findByEmpresaIdUsuario(Long idUsuario);

    List<Reto> findByEmpresa_IdUsuario(Long idUsuario);

    // 👇 Para la web pública (solo retos no bloqueados)
    List<Reto> findByBloqueadoFalse();
}
