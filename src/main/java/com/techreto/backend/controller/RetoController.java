package com.techreto.backend.controller;

import com.techreto.backend.model.Reto;
import com.techreto.backend.service.RetoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/retos")
@CrossOrigin(origins = "*")
public class RetoController {

    private final RetoService retoService;

    public RetoController(RetoService retoService) {
        this.retoService = retoService;
    }

    @GetMapping
    public List<Reto> listar() {
        return retoService.findAll();
    }

    @GetMapping("/{id}")
    public Reto obtener(@PathVariable Long id) {
        return retoService.findById(id);
    }

    @PostMapping
    public Reto crear(@RequestBody Reto reto) {
        return retoService.save(reto);
    }

    @PutMapping("/{id}")
    public Reto actualizar(@PathVariable Long id, @RequestBody Reto reto) {
        return retoService.update(id, reto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        retoService.delete(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Reto> listarPorEstado(@PathVariable String estado) {
        return retoService.findByEstado(estado);
    }
}
