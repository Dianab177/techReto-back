package com.techreto.backend.controller;

import com.techreto.backend.model.Reto;
import com.techreto.backend.repository.RetoRepository;
import com.techreto.backend.service.RetoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/retos")
@CrossOrigin(origins = "http://localhost:5173")
public class RetoController {

    private final RetoService retoService;
    private final RetoRepository retoRepository;

    public RetoController(RetoService retoService, RetoRepository retoRepository) {
        this.retoService = retoService;
        this.retoRepository = retoRepository;
    }

    //   Obtener todos los retos
    @GetMapping
    public List<Reto> listar() {
        return retoService.listar();
    }

    //   Obtener un reto por ID
    @GetMapping("/{id}")
    public Reto obtenerPorId(@PathVariable Long id) {
        return retoService.obtenerPorId(id);
    }

    //   Crear reto
    @PostMapping
    public Reto crear(@RequestBody Reto reto) {
        return retoService.guardar(reto);
    }

    //  Actualizar reto
    @PutMapping("/{id}")
    public Reto actualizar(@PathVariable Long id, @RequestBody Reto reto) {
        return retoService.actualizar(id, reto);
    }

    //  Eliminar reto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            retoService.eliminar(id);
            return ResponseEntity.ok("Reto eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error eliminando reto: " + e.getMessage());
        }
    }

    // Retos creados por una empresa
    @GetMapping("/empresa/{idEmpresa}")
    public List<Reto> obtenerRetosPorEmpresa(@PathVariable Long idEmpresa) {
        return retoRepository.findByEmpresaIdUsuario(idEmpresa);
    }
}
