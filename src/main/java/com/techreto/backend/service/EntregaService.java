package com.techreto.backend.service;

import com.techreto.backend.model.Entrega;
import com.techreto.backend.model.Inscripcion;
import com.techreto.backend.repository.EntregaRepository;
import com.techreto.backend.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final InscripcionRepository inscripcionRepository;

    public EntregaService(EntregaRepository entregaRepository, InscripcionRepository inscripcionRepository) {
        this.entregaRepository = entregaRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public Entrega guardar(Entrega entrega) {
        if (entrega.getInscripcion() != null && entrega.getInscripcion().getIdInscripcion() != null) {
            Inscripcion inscripcion = inscripcionRepository.findById(entrega.getInscripcion().getIdInscripcion())
                    .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
            entrega.setInscripcion(inscripcion);
        }
        return entregaRepository.save(entrega);
    }

    public Entrega actualizar(Long id, Entrega entregaActualizada) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        entrega.setUrlRepo(entregaActualizada.getUrlRepo());
        entrega.setComentario(entregaActualizada.getComentario());

        return entregaRepository.save(entrega);
    }

    public void eliminar(Long id) {
        entregaRepository.deleteById(id);
    }

    public List<Entrega> listarPorInscripcion(Long idInscripcion) {
        return entregaRepository.findByInscripcion_IdInscripcion(idInscripcion);
    }

    public Entrega buscarPorId(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));
    }

}
