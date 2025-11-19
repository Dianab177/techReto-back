package com.techreto.backend.controller;

import com.techreto.backend.model.Reto;
import com.techreto.backend.model.Usuario;
import com.techreto.backend.model.Inscripcion;

import com.techreto.backend.repository.RetoRepository;
import com.techreto.backend.repository.UsuarioRepository;
import com.techreto.backend.repository.InscripcionRepository;

import com.techreto.backend.service.RetoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/retos")
@CrossOrigin(origins = "http://localhost:5173")
public class RetoController {

    private final RetoService retoService;
    private final RetoRepository retoRepository;
    private final UsuarioRepository usuarioRepository;
    private final InscripcionRepository inscripcionRepository;

    public RetoController(
            RetoService retoService,
            RetoRepository retoRepository,
            UsuarioRepository usuarioRepository,
            InscripcionRepository inscripcionRepository
    ) {
        this.retoService = retoService;
        this.retoRepository = retoRepository;
        this.usuarioRepository = usuarioRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    // =============================
    //   Retos visibles (no bloqueados)
    // =============================
    @GetMapping
    public List<Reto> listar() {
        return retoService.listar();
    }

    @GetMapping("/{id}")
    public Reto obtenerPorId(@PathVariable Long id) {
        return retoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Reto reto) {
        try {
            if (reto.getEmpresa() == null || reto.getEmpresa().getIdUsuario() == null) {
                return ResponseEntity.badRequest().body("Debe especificarse una empresa válida para el reto");
            }

            Usuario empresa = usuarioRepository.findById(reto.getEmpresa().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

            reto.setEmpresa(empresa);

            Reto nuevo = retoService.guardar(reto);
            return ResponseEntity.ok(nuevo);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creando reto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Reto actualizar(@PathVariable Long id, @RequestBody Reto reto) {
        return retoService.actualizar(id, reto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            retoService.eliminar(id);
            return ResponseEntity.ok("Reto eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error eliminando reto: " + e.getMessage());
        }
    }

    // =============================
    //   Retos por empresa (SIN inscripciones)
    // =============================
    @GetMapping("/empresa/{idEmpresa}")
    public List<Reto> obtenerRetosPorEmpresa(@PathVariable Long idEmpresa) {
        return retoRepository.findByEmpresa_IdUsuario(idEmpresa);
    }

    // =============================
    //   Retos + inscripciones (para PerfilEmpresa)
    // =============================
    @GetMapping("/empresa/{idEmpresa}/inscripciones")
    public List<Map<String, Object>> obtenerRetosConInscripciones(@PathVariable Long idEmpresa) {

        List<Reto> retos = retoRepository.findByEmpresa_IdUsuario(idEmpresa);

        return retos.stream().map(reto -> {

            Map<String, Object> data = new HashMap<>();
            data.put("idReto", reto.getIdReto());
            data.put("titulo", reto.getTitulo());
            data.put("estado", reto.getEstado());
            data.put("bloqueado", reto.isBloqueado());

            // 🔹 Obtener inscripciones del reto
            List<Inscripcion> inscripciones = inscripcionRepository.findByReto_IdReto(reto.getIdReto());

            // 🔹 Formatear para el frontend
            List<Map<String, Object>> listaIns = inscripciones.stream().map(ins -> {
                Map<String, Object> insData = new HashMap<>();

                insData.put("idInscripcion", ins.getIdInscripcion());
                insData.put("estado", ins.getEstado());
                insData.put("fechaInscripcion", ins.getFechaInscripcion());

                // Usuario dentro de la inscripción
                Map<String, Object> usuario = new HashMap<>();
                usuario.put("idUsuario", ins.getUsuario().getIdUsuario());
                usuario.put("nombre", ins.getUsuario().getNombre());
                usuario.put("competencias", ins.getUsuario().getCompetencias());

                insData.put("usuario", usuario);

                return insData;
            }).collect(Collectors.toList());

            data.put("inscripciones", listaIns);

            return data;

        }).collect(Collectors.toList());
    }

    // =============================
    //   ADMIN: todos los retos
    // =============================
    @GetMapping("/admin/todos")
    public List<Reto> listarTodosAdmin() {
        return retoService.listarTodosAdmin();
    }

    // =============================
    //   ADMIN: toggle bloqueo
    // =============================
    @PutMapping("/{id}/toggle-bloqueo")
    public Reto toggleBloqueo(@PathVariable Long id) {
        Reto reto = retoService.obtenerPorId(id);
        boolean nuevoEstado = !reto.isBloqueado();
        return retoService.cambiarBloqueo(id, nuevoEstado);
    }
}
