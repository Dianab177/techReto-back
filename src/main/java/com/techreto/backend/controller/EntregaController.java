package com.techreto.backend.controller;

import com.techreto.backend.model.Entrega;
import com.techreto.backend.service.EntregaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaService.listar();
    }

    @GetMapping("/{id}")
    public Entrega obtenerPorId(@PathVariable Long id) {
        return entregaService.obtenerPorId(id);
    }

    @PostMapping
    public Entrega guardar(@RequestBody Entrega entrega) {
        return entregaService.guardar(entrega);
    }

    @PutMapping("/{id}")
    public Entrega actualizar(@PathVariable Long id, @RequestBody Entrega entrega) {
        return entregaService.actualizar(id, entrega);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        entregaService.eliminar(id);
    }
}
