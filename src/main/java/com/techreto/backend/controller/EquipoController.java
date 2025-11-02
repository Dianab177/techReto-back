package com.techreto.backend.controller;

import com.techreto.backend.model.Equipo;
import com.techreto.backend.service.EquipoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "*")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // 🔹 Listar todos los equipos
    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listar();
    }

    // 🔹 Obtener equipo por ID
    @GetMapping("/{id}")
    public Equipo obtenerPorId(@PathVariable Long id) {
        return equipoService.obtenerPorId(id);
    }

    // 🔹 Crear nuevo equipo
    @PostMapping
    public Equipo guardar(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }

    // 🔹 Actualizar equipo existente
    @PutMapping("/{id}")
    public Equipo actualizar(@PathVariable Long id, @RequestBody Equipo detalles) {
        return equipoService.actualizar(id, detalles);
    }

    // 🔹 Eliminar equipo por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
    }
}
