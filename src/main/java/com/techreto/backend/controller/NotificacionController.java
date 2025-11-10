package com.techreto.backend.controller;

import com.techreto.backend.model.Notificacion;
import com.techreto.backend.service.NotificacionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }


    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listar();
    }


    @GetMapping("/{id}")
    public Notificacion obtenerPorId(@PathVariable Long id) {
        return notificacionService.obtenerPorId(id);
    }


    @PostMapping
    public Notificacion crear(@RequestBody Notificacion notificacion) {
        return notificacionService.guardar(notificacion);
    }


    @PutMapping("/{id}")
    public Notificacion actualizar(@PathVariable Long id, @RequestBody Notificacion notificacionDetalles) {
        return notificacionService.actualizar(id, notificacionDetalles);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        notificacionService.eliminar(id);
    }
}
