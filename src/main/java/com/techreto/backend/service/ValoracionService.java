package com.techreto.backend.service;

import com.techreto.backend.model.Entrega;
import com.techreto.backend.model.Valoracion;
import com.techreto.backend.repository.EntregaRepository;
import com.techreto.backend.repository.ValoracionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ValoracionService {

    private final ValoracionRepository valoracionRepository;
    private final EntregaRepository entregaRepository;

    public ValoracionService(ValoracionRepository valoracionRepository, EntregaRepository entregaRepository) {
        this.valoracionRepository = valoracionRepository;
        this.entregaRepository = entregaRepository;
    }

    public List<Valoracion> listar() {
        return valoracionRepository.findAll();
    }

    public Valoracion guardar(Valoracion valoracion) {
        if (valoracion.getEntrega() != null && valoracion.getEntrega().getIdEntrega() != null) {
            Entrega entrega = entregaRepository.findById(valoracion.getEntrega().getIdEntrega())
                    .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));
            valoracion.setEntrega(entrega);
        }
        return valoracionRepository.save(valoracion);
    }

    public Valoracion actualizar(Long id, Valoracion valoracionActualizada) {
        Valoracion valoracion = valoracionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada"));

        valoracion.setPuntuacion(valoracionActualizada.getPuntuacion());
        valoracion.setComentario(valoracionActualizada.getComentario());

        return valoracionRepository.save(valoracion);
    }

    public void eliminar(Long id) {
        valoracionRepository.deleteById(id);
    }

    public Valoracion buscarPorEntrega(Long idEntrega) {
        return valoracionRepository.findByEntrega_IdEntrega(idEntrega);
    }
}
