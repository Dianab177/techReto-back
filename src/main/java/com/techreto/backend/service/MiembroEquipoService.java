package com.techreto.backend.service;

import com.techreto.backend.model.MiembroEquipo;
import com.techreto.backend.model.MiembroEquipoId;
import com.techreto.backend.repository.MiembroEquipoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MiembroEquipoService {

    private final MiembroEquipoRepository miembroEquipoRepository;

    public MiembroEquipoService(MiembroEquipoRepository miembroEquipoRepository) {
        this.miembroEquipoRepository = miembroEquipoRepository;
    }

    // ✅ Listar todos los miembros
    public List<MiembroEquipo> listar() {
        return miembroEquipoRepository.findAll();
    }

    // ✅ Buscar por id compuesto (equipo + usuario)
    public MiembroEquipo findById(Long idEquipo, Long idUsuario) {
        MiembroEquipoId id = new MiembroEquipoId(idEquipo, idUsuario);
        return miembroEquipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));
    }

    // ✅ Crear o actualizar un miembro
    public MiembroEquipo save(MiembroEquipo miembro) {
        return miembroEquipoRepository.save(miembro);
    }

    // ✅ Actualizar datos de un miembro existente
    // ✅ Actualizar datos de un miembro existente
    public MiembroEquipo update(Long idEquipo, Long idUsuario, MiembroEquipo miembroDetalles) {
        MiembroEquipo miembro = findById(idEquipo, idUsuario);
        // Si más adelante agregas otros campos editables, los actualizarás aquí
        return miembroEquipoRepository.save(miembro);
    }


    // ✅ Eliminar miembro
    public void delete(Long idEquipo, Long idUsuario) {
        MiembroEquipoId id = new MiembroEquipoId(idEquipo, idUsuario);
        miembroEquipoRepository.deleteById(id);
    }

    // ✅ Listar por equipo
    public List<MiembroEquipo> listarPorEquipo(Long idEquipo) {
        return miembroEquipoRepository.findAll()
                .stream()
                .filter(m -> m.getEquipo().getIdEquipo().equals(idEquipo))
                .toList();
    }

    // ✅ Listar por usuario
    public List<MiembroEquipo> listarPorUsuario(Long idUsuario) {
        return miembroEquipoRepository.findAll()
                .stream()
                .filter(m -> m.getUsuario().getIdUsuario().equals(idUsuario))
                .toList();
    }
}
