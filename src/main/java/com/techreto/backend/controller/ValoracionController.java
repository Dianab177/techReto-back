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

    @GetMapping
    public List<Valoracion> listar() {
        return valoracionService.listar();
    }

    @PostMapping
    public Valoracion guardar(@RequestBody Valoracion valoracion) {
        return valoracionService.guardar(valoracion);
    }

    @PutMapping("/{id}")
    public Valoracion actualizar(@PathVariable Long id, @RequestBody Valoracion valoracion) {
        return valoracionService.actualizar(id, valoracion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        valoracionService.eliminar(id);
    }

    @GetMapping("/entrega/{idEntrega}")
    public Valoracion buscarPorEntrega(@PathVariable Long idEntrega) {
        return valoracionService.buscarPorEntrega(idEntrega);
    }
}
