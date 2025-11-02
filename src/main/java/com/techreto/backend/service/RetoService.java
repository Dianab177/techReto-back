package com.techreto.backend.service;

import com.techreto.backend.model.Reto;
import com.techreto.backend.repository.RetoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RetoService {

    private final RetoRepository retoRepository;

    public RetoService(RetoRepository retoRepository) {
        this.retoRepository = retoRepository;
    }

    public List<Reto> listar() {
        return retoRepository.findAll();
    }

    public Reto guardar(Reto reto) {
        return retoRepository.save(reto);
    }

    public Reto obtenerPorId(Long id) {
        return retoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));
    }

    public Reto actualizar(Long id, Reto detalles) {
        Reto reto = obtenerPorId(id);
        reto.setTitulo(detalles.getTitulo());
        reto.setDescripcion(detalles.getDescripcion());
        reto.setEstado(detalles.getEstado());
        reto.setTipo(detalles.getTipo());
        reto.setRecompensa(detalles.getRecompensa());
        return retoRepository.save(reto);
    }

    public void eliminar(Long id) {
        retoRepository.deleteById(id);
    }
}
