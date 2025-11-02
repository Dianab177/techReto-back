package com.techreto.backend;

import com.techreto.backend.model.Usuario;
import com.techreto.backend.model.Reto;
import com.techreto.backend.service.UsuarioService;
import com.techreto.backend.service.RetoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
class TechRetoBackendApplicationTests {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RetoService retoService;

    @Test
    void contextLoads() {
        // Verifica que el contexto Spring se levanta correctamente
        assertThat(usuarioService).isNotNull();
        assertThat(retoService).isNotNull();
    }

    @Test
    void testCrearUsuario() {
        // 🧩 Email único con timestamp
        String emailUnico = "testuser" + System.currentTimeMillis() + "@techreto.com";

        Usuario nuevo = new Usuario();
        nuevo.setNombre("Usuario Test");
        nuevo.setEmail(emailUnico);
        nuevo.setPassword("1234");
        nuevo.setRol("PARTICIPANTE");

        Usuario guardado = usuarioService.guardar(nuevo);

        assertThat(guardado.getIdUsuario()).isNotNull();
        assertThat(guardado.getEmail()).isEqualTo(emailUnico);
    }

    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = usuarioService.listar();
        assertThat(usuarios).isNotEmpty(); // Verifica que haya usuarios cargados
    }

    @Test
    void testListarRetos() {
        List<Reto> retos = retoService.listar();
        assertThat(retos).isNotNull();
        assertThat(retos).isNotEmpty(); // Debes tener retos de ejemplo en la BD
    }
}
