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

    public List<MiembroEquipo> listar() {
        return miembroEquipoRepository.findAll();
    }

    public MiembroEquipo guardar(MiembroEquipo miembro) {
        return miembroEquipoRepository.save(miembro);
    }

    public MiembroEquipo obtenerPorId(Long idEquipo, Long idUsuario) {
        MiembroEquipoId id = new MiembroEquipoId(idEquipo, idUsuario);
        return miembroEquipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));
    }

    public MiembroEquipo actualizar(Long idEquipo, Long idUsuario, MiembroEquipo detalles) {
        MiembroEquipo miembro = obtenerPorId(idEquipo, idUsuario);
        if (detalles.getRol() != null) miembro.setRol(detalles.getRol());
        return miembroEquipoRepository.save(miembro);
    }

    public void eliminar(Long idEquipo, Long idUsuario) {
        MiembroEquipoId id = new MiembroEquipoId(idEquipo, idUsuario);
        miembroEquipoRepository.deleteById(id);
    }
}
