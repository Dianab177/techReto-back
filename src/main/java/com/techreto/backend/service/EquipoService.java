package com.techreto.backend.service;

import com.techreto.backend.model.Equipo;
import com.techreto.backend.repository.EquipoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // Listar todos los equipos
    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    // Buscar por ID
    public Equipo findById(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    // Crear nuevo equipo
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // Actualizar equipo existente
    public Equipo update(Long id, Equipo equipoDetalles) {
        Equipo equipo = findById(id);
        equipo.setNombre(equipoDetalles.getNombre());
        equipo.setDescripcion(equipoDetalles.getDescripcion());
        return equipoRepository.save(equipo);
    }

    // Eliminar equipo
    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
}
