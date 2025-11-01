package com.techreto.backend.repository;

import com.techreto.backend.model.MiembroEquipo;
import com.techreto.backend.model.MiembroEquipoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiembroEquipoRepository extends JpaRepository<MiembroEquipo, MiembroEquipoId> {
}
