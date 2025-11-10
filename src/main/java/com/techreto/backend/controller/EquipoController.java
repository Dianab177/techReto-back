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

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listar();
    }


    @GetMapping("/{id}")
    public Equipo obtenerPorId(@PathVariable Long id) {
        return equipoService.obtenerPorId(id);
    }


    @PostMapping
    public Equipo guardar(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }


    @PutMapping("/{id}")
    public Equipo actualizar(@PathVariable Long id, @RequestBody Equipo detalles) {
        return equipoService.actualizar(id, detalles);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
    }
}
