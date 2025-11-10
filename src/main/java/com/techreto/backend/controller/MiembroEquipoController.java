package com.techreto.backend.controller;

import com.techreto.backend.model.MiembroEquipo;
import com.techreto.backend.service.MiembroEquipoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/miembros")
@CrossOrigin(origins = "*")
public class MiembroEquipoController {

    private final MiembroEquipoService miembroEquipoService;

    public MiembroEquipoController(MiembroEquipoService miembroEquipoService) {
        this.miembroEquipoService = miembroEquipoService;
    }


    @GetMapping
    public List<MiembroEquipo> listar() {
        return miembroEquipoService.listar();
    }


    @GetMapping("/{idEquipo}/{idUsuario}")
    public MiembroEquipo obtenerPorId(@PathVariable Long idEquipo, @PathVariable Long idUsuario) {
        return miembroEquipoService.obtenerPorId(idEquipo, idUsuario);
    }


    @PostMapping
    public MiembroEquipo agregar(@RequestBody MiembroEquipo miembro) {
        return miembroEquipoService.guardar(miembro);
    }


    @PutMapping("/{idEquipo}/{idUsuario}")
    public MiembroEquipo actualizar(
            @PathVariable Long idEquipo,
            @PathVariable Long idUsuario,
            @RequestBody MiembroEquipo miembro) {
        return miembroEquipoService.actualizar(idEquipo, idUsuario, miembro);
    }


    @DeleteMapping("/{idEquipo}/{idUsuario}")
    public void eliminar(@PathVariable Long idEquipo, @PathVariable Long idUsuario) {
        miembroEquipoService.eliminar(idEquipo, idUsuario);
    }
}
