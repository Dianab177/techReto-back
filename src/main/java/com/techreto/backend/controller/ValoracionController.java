package com.techreto.backend.controller;

import com.techreto.backend.model.Valoracion;
import com.techreto.backend.service.ValoracionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "*")
public class ValoracionController {

    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    // 🔹 Listar todas las valoraciones
    @GetMapping
    public List<Valoracion> listar() {
        return valoracionService.listar();
    }

    // 🔹 Obtener valoración por ID
    @GetMapping("/{id}")
    public Valoracion obtenerPorId(@PathVariable Long id) {
        return valoracionService.obtenerPorId(id);
    }

    // 🔹 Crear nueva valoración
    @PostMapping
    public Valoracion guardar(@RequestBody Valoracion valoracion) {
        return valoracionService.guardar(valoracion);
    }

    // 🔹 Actualizar una valoración existente
    @PutMapping("/{id}")
    public Valoracion actualizar(@PathVariable Long id, @RequestBody Valoracion valoracion) {
        return valoracionService.actualizar(id, valoracion);
    }

    // 🔹 Eliminar valoración
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        valoracionService.eliminar(id);
    }
}
