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

    // ✅ Listar todos
    public List<MiembroEquipo> listar() {
        return miembroEquipoRepository.findAll();
    }

    // ✅ Guardar miembro (crea el id compuesto automáticamente)
    public MiembroEquipo guardar(MiembroEquipo miembro) {
        if (miembro.getId() == null && miembro.getEquipo() != null && miembro.getUsuario() != null) {
            miembro.setId(new MiembroEquipoId(
                    miembro.getEquipo().getIdEquipo(),
                    miembro.getUsuario().getIdUsuario()
            ));
        }
        return miembroEquipoRepository.save(miembro);
    }

    // ✅ Buscar por id compuesto
    public MiembroEquipo obtenerPorId(Long idEquipo, Long idUsuario) {
        MiembroEquipoId id = new MiembroEquipoId(idEquipo, idUsuario);
        return miembroEquipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));
    }

    // ✅ Actualizar (por ejemplo, el rol)
    public MiembroEquipo actualizar(Long idEquipo, Long idUsuario, MiembroEquipo miembroDetalles) {
        MiembroEquipo miembro = obtenerPorId(idEquipo, idUsuario);

        if (miembroDetalles.getRol() != null) {
            miembro.setRol(miembroDetalles.getRol());
        }

        return miembroEquipoRepository.save(miembro);
    }

    // ✅ Eliminar miembro
    public void eliminar(Long idEquipo, Long idUsuario) {
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
