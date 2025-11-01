package com.techreto.backend.service;

import com.techreto.backend.model.*;
import com.techreto.backend.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final UsuarioRepository usuarioRepository;
    private final RetoRepository retoRepository;
    private final EquipoRepository equipoRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository,
                              UsuarioRepository usuarioRepository,
                              RetoRepository retoRepository,
                              EquipoRepository equipoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.usuarioRepository = usuarioRepository;
        this.retoRepository = retoRepository;
        this.equipoRepository = equipoRepository;
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public List<Inscripcion> listarPorUsuario(Long idUsuario) {
        return inscripcionRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public List<Inscripcion> listarPorReto(Long idReto) {
        return inscripcionRepository.findByReto_IdReto(idReto);
    }

    public List<Inscripcion> listarPorEquipo(Long idEquipo) {
        return inscripcionRepository.findByEquipo_IdEquipo(idEquipo);
    }

    public Inscripcion guardar(Inscripcion inscripcion) {
        if (inscripcion.getUsuario() != null && inscripcion.getUsuario().getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(inscripcion.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            inscripcion.setUsuario(usuario);
        }

        if (inscripcion.getReto() != null && inscripcion.getReto().getIdReto() != null) {
            Reto reto = retoRepository.findById(inscripcion.getReto().getIdReto())
                    .orElseThrow(() -> new RuntimeException("Reto no encontrado"));
            inscripcion.setReto(reto);
        }

        if (inscripcion.getEquipo() != null && inscripcion.getEquipo().getIdEquipo() != null) {
            Equipo equipo = equipoRepository.findById(inscripcion.getEquipo().getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            inscripcion.setEquipo(equipo);
        }

        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizar(Long id, Inscripcion inscripcionActualizada) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        inscripcion.setEstado(inscripcionActualizada.getEstado());
        inscripcion.setFechaInscripcion(inscripcionActualizada.getFechaInscripcion());

        if (inscripcionActualizada.getUsuario() != null && inscripcionActualizada.getUsuario().getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(inscripcionActualizada.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            inscripcion.setUsuario(usuario);
        }

        if (inscripcionActualizada.getReto() != null && inscripcionActualizada.getReto().getIdReto() != null) {
            Reto reto = retoRepository.findById(inscripcionActualizada.getReto().getIdReto())
                    .orElseThrow(() -> new RuntimeException("Reto no encontrado"));
            inscripcion.setReto(reto);
        }

        if (inscripcionActualizada.getEquipo() != null && inscripcionActualizada.getEquipo().getIdEquipo() != null) {
            Equipo equipo = equipoRepository.findById(inscripcionActualizada.getEquipo().getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            inscripcion.setEquipo(equipo);
        }

        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
