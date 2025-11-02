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
        return retoService.listar();
    }

    @GetMapping("/{id}")
    public Reto obtenerPorId(@PathVariable Long id) {
        return retoService.obtenerPorId(id);
    }

    @PostMapping
    public Reto guardar(@RequestBody Reto reto) {
        return retoService.guardar(reto);
    }

    @PutMapping("/{id}")
    public Reto actualizar(@PathVariable Long id, @RequestBody Reto reto) {
        return retoService.actualizar(id, reto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        retoService.eliminar(id);
    }
}
