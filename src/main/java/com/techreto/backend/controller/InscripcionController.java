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
import com.techreto.backend.dto.EntregaDTO;

import java.util.List;
import java.util.Optional;

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

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Reto reto = retoRepository.findById(request.getIdReto())
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        boolean yaInscrito = inscripcionRepository
                .findByUsuarioAndReto(usuario, reto)
                .isPresent();

        if (yaInscrito) {
            return ResponseEntity.badRequest().body("El usuario ya está inscrito");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuario);
        inscripcion.setReto(reto);

        return ResponseEntity.ok(inscripcionService.guardar(inscripcion));
    }

    @GetMapping("/{id}")
    public Inscripcion obtenerPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        return inscripcionService.actualizar(id, inscripcion);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Inscripcion> listarPorUsuario(@PathVariable Long idUsuario) {
        return inscripcionService.listarPorUsuario(idUsuario);
    }

    @GetMapping("/reto/{idReto}")
    public List<Inscripcion> obtenerPorReto(@PathVariable Long idReto) {
        return inscripcionRepository.findByReto_IdReto(idReto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
    }

    @PutMapping("/{id}/ocultar")
    public ResponseEntity<?> ocultar(@PathVariable Long id) {
        Inscripcion i = inscripcionService.ocultar(id);
        return ResponseEntity.ok(i);
    }
    @GetMapping("/admin/todas")
    public List<Inscripcion> listarTodas() {
        return inscripcionService.listar();
    }


    @PutMapping("/{id}/entregar")
    public ResponseEntity<?> entregarReto(
            @PathVariable Long id,
            @RequestBody EntregaDTO entregaDTO
    ) {
        Optional<Inscripcion> optional = inscripcionRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Inscripcion inscripcion = optional.get();

        inscripcion.setEnlaceRepositorio(entregaDTO.getEnlaceRepositorio());
        inscripcion.setEnlaceFigma(entregaDTO.getEnlaceFigma());
        inscripcion.setEnlaceDemo(entregaDTO.getEnlaceDemo());
        inscripcion.setEstadoEntrega("ENTREGADO");

        inscripcionRepository.save(inscripcion);

        return ResponseEntity.ok("Entrega realizada con éxito");
    }
    


}
