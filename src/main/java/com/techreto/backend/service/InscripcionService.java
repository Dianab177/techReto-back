package com.techreto.backend.service;

import com.techreto.backend.model.Inscripcion;
import com.techreto.backend.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public List<Inscripcion> listarPorUsuario(Long idUsuario) {
        return inscripcionRepository.findByUsuario_IdUsuarioAndOcultoFalse(idUsuario);
    }

    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion obtenerPorId(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }

    public Inscripcion actualizar(Long id, Inscripcion detalles) {

        Inscripcion inscripcion = obtenerPorId(id);

        // Estado entrega
        if (detalles.getEstadoEntrega() != null) {
            inscripcion.setEstadoEntrega(detalles.getEstadoEntrega());
        }

        // Estado aprobación
        if (detalles.getEstadoAprobacion() != null) {
            inscripcion.setEstadoAprobacion(detalles.getEstadoAprobacion());
        }

        // Enlaces
        if (detalles.getEnlaceRepositorio() != null) {
            inscripcion.setEnlaceRepositorio(detalles.getEnlaceRepositorio());
        }
        if (detalles.getEnlaceFigma() != null) {
            inscripcion.setEnlaceFigma(detalles.getEnlaceFigma());
        }
        if (detalles.getEnlaceDemo() != null) {
            inscripcion.setEnlaceDemo(detalles.getEnlaceDemo());
        }

        return inscripcionRepository.save(inscripcion);
    }


    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }

    public Inscripcion ocultar(Long id) {
        Inscripcion inscripcion = obtenerPorId(id);
        inscripcion.setOculto(true);
        return inscripcionRepository.save(inscripcion);
    }
}
