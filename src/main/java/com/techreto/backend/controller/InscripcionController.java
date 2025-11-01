package com.techreto.backend.controller;

import com.techreto.backend.model.Inscripcion;
import com.techreto.backend.service.InscripcionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    // 📋 GET - Listar todas
    @GetMapping
    public List<Inscripcion> listar() {
        return inscripcionService.listar();
    }

    // ➕ POST - Crear nueva inscripción
    @PostMapping
    public Inscripcion guardar(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.guardar(inscripcion);
    }

    // ✏️ PUT - Actualizar inscripción existente
    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.actualizar(id, inscripcion);
    }

    // ❌ DELETE - Eliminar inscripción por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
    }

    // 🔍 GET - Listar por usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Inscripcion> listarPorUsuario(@PathVariable Long idUsuario) {
        return inscripcionService.listarPorUsuario(idUsuario);
    }

    // 🔍 GET - Listar por reto
    @GetMapping("/reto/{idReto}")
    public List<Inscripcion> listarPorReto(@PathVariable Long idReto) {
        return inscripcionService.listarPorReto(idReto);
    }

    // 🔍 GET - Listar por equipo
    @GetMapping("/equipo/{idEquipo}")
    public List<Inscripcion> listarPorEquipo(@PathVariable Long idEquipo) {
        return inscripcionService.listarPorEquipo(idEquipo);
    }
}
