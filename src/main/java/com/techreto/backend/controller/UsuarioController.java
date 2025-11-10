package com.techreto.backend.controller;

import com.techreto.backend.model.Usuario;
import com.techreto.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Usuario encontrado = usuarioService.obtenerPorEmail(usuario.getEmail());

        if (encontrado == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!encontrado.getPassword().equals(usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return encontrado;
    }
}
