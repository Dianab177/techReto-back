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

    // 🔹 Listar todas las inscripciones
    @GetMapping
    public List<Inscripcion> listar() {
        return inscripcionService.listar();
    }

    // 🔹 Obtener inscripción por ID
    @GetMapping("/{id}")
    public Inscripcion obtenerPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id);
    }

    // 🔹 Crear nueva inscripción
    @PostMapping
    public Inscripcion guardar(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.guardar(inscripcion);
    }

    // 🔹 Actualizar inscripción existente
    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.actualizar(id, inscripcion);
    }

    // 🔹 Eliminar inscripción
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
    }
}
