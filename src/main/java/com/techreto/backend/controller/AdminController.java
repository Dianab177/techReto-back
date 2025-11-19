package com.techreto.backend.controller;

import com.techreto.backend.model.Reto;
import com.techreto.backend.model.Usuario;
import com.techreto.backend.repository.UsuarioRepository;
import com.techreto.backend.service.RetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final RetoService retoService;

    public AdminController(UsuarioRepository usuarioRepository,
                           RetoService retoService) {
        this.usuarioRepository = usuarioRepository;
        this.retoService = retoService;
    }

    // ======================
    //   USUARIOS
    // ======================

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // DTO sencillo para cambiar rol
    public static class RolRequest {
        private String rol;

        public String getRol() { return rol; }
        public void setRol(String rol) { this.rol = rol; }
    }

    @PutMapping("/usuarios/{id}/rol")
    public ResponseEntity<?> cambiarRol(@PathVariable Long id,
                                        @RequestBody RolRequest body) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setRol(body.getRol());
                    usuarioRepository.save(usuario);
                    return ResponseEntity.ok(usuario);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ======================
    //   RETOS
    // ======================

    @GetMapping("/retos")
    public List<Reto> listarRetosAdmin() {
        return retoService.listarTodosAdmin();
    }


}
