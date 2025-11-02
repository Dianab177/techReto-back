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

    public List<Equipo> listar() {
        return equipoRepository.findAll();
    }

    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo obtenerPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    public Equipo actualizar(Long id, Equipo detalles) {
        Equipo equipo = obtenerPorId(id);
        equipo.setNombre(detalles.getNombre());
        equipo.setDescripcion(detalles.getDescripcion());
        return equipoRepository.save(equipo);
    }

    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }
}
