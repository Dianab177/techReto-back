package com.techreto.backend.service;

import com.techreto.backend.model.Notificacion;
import com.techreto.backend.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public List<Notificacion> listarPorUsuario(Long idUsuario) {
        return notificacionRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public Notificacion obtenerPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    public Notificacion guardar(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizar(Long id, Notificacion notificacionDetalles) {
        Notificacion n = obtenerPorId(id);
        if (notificacionDetalles.getMensaje() != null)
            n.setMensaje(notificacionDetalles.getMensaje());
        n.setLeida(notificacionDetalles.isLeida());
        return notificacionRepository.save(n);
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}
