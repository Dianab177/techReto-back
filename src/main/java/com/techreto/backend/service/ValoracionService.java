package com.techreto.backend.service;

import com.techreto.backend.model.Valoracion;
import com.techreto.backend.repository.ValoracionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;

    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public List<Valoracion> listar() {
        return valoracionRepository.findAll();
    }

    public Valoracion guardar(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    public Valoracion obtenerPorId(Long id) {
        return valoracionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));
    }

    public Valoracion actualizar(Long id, Valoracion detalles) {
        Valoracion valoracion = obtenerPorId(id);
        valoracion.setPuntuacion(detalles.getPuntuacion());
        valoracion.setComentario(detalles.getComentario());
        return valoracionRepository.save(valoracion);
    }

    public void eliminar(Long id) {
        valoracionRepository.deleteById(id);
    }
}
