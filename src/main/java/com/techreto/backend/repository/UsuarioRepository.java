package com.techreto.backend.repository;

import com.techreto.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Puedes añadir métodos personalizados si los necesitas más adelante
}
