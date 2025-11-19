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

    // ============================================
    // LISTADOS
    // ============================================

    // 👇 Para la web pública: solo retos NO bloqueados
    public List<Reto> listar() {
        return retoRepository.findByBloqueadoFalse();
    }

    // 👇 Para el ADMIN: ver TODOS los retos, bloqueados y no bloqueados
    public List<Reto> listarTodosAdmin() {
        return retoRepository.findAll();
    }

    // ============================================
    // CRUD BÁSICO
    // ============================================

    public Reto obtenerPorId(Long id) {
        return retoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));
    }

    public Reto guardar(Reto reto) {
        return retoRepository.save(reto);
    }

    public Reto actualizar(Long id, Reto datos) {
        Reto existente = obtenerPorId(id);

        existente.setTitulo(datos.getTitulo());
        existente.setDescripcion(datos.getDescripcion());
        existente.setTipo(datos.getTipo());
        existente.setEstado(datos.getEstado());
        existente.setRecompensa(datos.getRecompensa());
        existente.setFechaInicio(datos.getFechaInicio());
        existente.setFechaFin(datos.getFechaFin());

        return retoRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!retoRepository.existsById(id)) {
            throw new RuntimeException("Reto no encontrado");
        }
        retoRepository.deleteById(id);
    }

    // ============================================
    // BLOQUEAR / DESBLOQUEAR
    // ============================================

    public Reto cambiarBloqueo(Long id, boolean bloquear) {
        Reto reto = retoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        reto.setBloqueado(bloquear);
        return retoRepository.save(reto);
    }
}
