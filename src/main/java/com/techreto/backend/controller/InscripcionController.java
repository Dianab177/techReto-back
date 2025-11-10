package com.techreto.backend.controller;

import com.techreto.backend.dto.InscripcionRequest;
import com.techreto.backend.model.Inscripcion;
import com.techreto.backend.model.Reto;
import com.techreto.backend.model.Usuario;
import com.techreto.backend.repository.InscripcionRepository;
import com.techreto.backend.repository.RetoRepository;
import com.techreto.backend.repository.UsuarioRepository;
import com.techreto.backend.service.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "http://localhost:5173")
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final UsuarioRepository usuarioRepository;
    private final RetoRepository retoRepository;
    private final InscripcionRepository inscripcionRepository;

    public InscripcionController(
            InscripcionService inscripcionService,
            UsuarioRepository usuarioRepository,
            RetoRepository retoRepository,
            InscripcionRepository inscripcionRepository
    ) {
        this.inscripcionService = inscripcionService;
        this.usuarioRepository = usuarioRepository;
        this.retoRepository = retoRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @GetMapping
    public List<Inscripcion> listar() {
        return inscripcionService.listar();
    }

    @PostMapping
    public ResponseEntity<?> inscribirse(@RequestBody InscripcionRequest request) {
        System.out.println("📥 Recibido en backend: idUsuario=" + request.getIdUsuario() + ", idReto=" + request.getIdReto());

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Reto reto = retoRepository.findById(request.getIdReto())
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        boolean yaInscrito = inscripcionRepository
                .findByUsuarioAndReto(usuario, reto)
                .isPresent();

        if (yaInscrito) {
            return ResponseEntity.badRequest().body("El usuario ya está inscrito en este reto.");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuario);
        inscripcion.setReto(reto);
        inscripcion.setEstado("PENDIENTE");

        Inscripcion nueva = inscripcionService.guardar(inscripcion);
        System.out.println("✅ Inscripción creada con éxito: ID " + nueva.getIdInscripcion());

        return ResponseEntity.ok(nueva);
    }

    @GetMapping("/{id}")
    public Inscripcion obtenerPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.actualizar(id, inscripcion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
    }
}
