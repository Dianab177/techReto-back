package com.techreto.backend.service;

import com.techreto.backend.model.Reto;
import com.techreto.backend.model.Usuario;
import com.techreto.backend.repository.RetoRepository;
import com.techreto.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RetoService {

    private final RetoRepository retoRepository;
    private final UsuarioRepository usuarioRepository; // ✅ Instancia, no clase estática

    // ✅ Inyección por constructor
    public RetoService(RetoRepository retoRepository, UsuarioRepository usuarioRepository) {
        this.retoRepository = retoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Reto> findAll() {
        return retoRepository.findAll();
    }

    public Reto findById(Long id) {
        return retoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));
    }

    public Reto save(Reto reto) {
        if (reto.getEmpresa() != null && reto.getEmpresa().getIdUsuario() != null) {
            Usuario empresa = usuarioRepository.findById(reto.getEmpresa().getIdUsuario()) // ✅
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
            reto.setEmpresa(empresa);
        }
        return retoRepository.save(reto);
    }

    public Reto update(Long id, Reto retoDetalles) {
        Reto reto = findById(id);
        reto.setTitulo(retoDetalles.getTitulo());
        reto.setDescripcion(retoDetalles.getDescripcion());
        reto.setTipo(retoDetalles.getTipo());
        reto.setEstado(retoDetalles.getEstado());
        reto.setRecompensa(retoDetalles.getRecompensa());
        reto.setFechaInicio(retoDetalles.getFechaInicio());
        reto.setFechaFin(retoDetalles.getFechaFin());
        reto.setEmpresa(retoDetalles.getEmpresa());
        return retoRepository.save(reto);
    }

    public void delete(Long id) {
        retoRepository.deleteById(id);
    }

    public List<Reto> findByEstado(String estado) {
        return retoRepository.findByEstado(estado);
    }


}
