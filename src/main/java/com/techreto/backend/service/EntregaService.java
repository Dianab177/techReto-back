package com.techreto.backend.service;

import com.techreto.backend.model.Entrega;
import com.techreto.backend.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public Entrega guardar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Entrega obtenerPorId(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));
    }

    public Entrega actualizar(Long id, Entrega detalles) {
        Entrega entrega = obtenerPorId(id);
        entrega.setUrlRepo(detalles.getUrlRepo());
        entrega.setComentario(detalles.getComentario());
        return entregaRepository.save(entrega);
    }

    public void eliminar(Long id) {
        entregaRepository.deleteById(id);
    }
}
