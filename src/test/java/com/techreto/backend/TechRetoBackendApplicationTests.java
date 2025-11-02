package com.techreto.backend;

import com.techreto.backend.model.*;
import com.techreto.backend.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TechRetoBackendApplicationTests {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RetoService retoService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private ValoracionService valoracionService;

    @Autowired
    private MiembroEquipoService miembroEquipoService;

    @Autowired
    private NotificacionService notificacionService;

    // 🔹 Verifica que todos los servicios se inyectan correctamente
    @Test
    void contextLoads() {
        assertThat(usuarioService).isNotNull();
        assertThat(retoService).isNotNull();
        assertThat(equipoService).isNotNull();
        assertThat(inscripcionService).isNotNull();
        assertThat(entregaService).isNotNull();
        assertThat(valoracionService).isNotNull();
        assertThat(miembroEquipoService).isNotNull();
        assertThat(notificacionService).isNotNull();
    }

    // 🔹 Test 1: Crear usuario
    @Test
    void testCrearUsuario() {
        String emailUnico = "testuser" + System.currentTimeMillis() + "@techreto.com";
        Usuario nuevo = new Usuario();
        nuevo.setNombre("Usuario Test");
        nuevo.setEmail(emailUnico);
        nuevo.setPassword("123456");
        nuevo.setRol("PARTICIPANTE");

        Usuario guardado = usuarioService.guardar(nuevo);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getIdUsuario()).isNotNull();
        assertThat(guardado.getEmail()).isEqualTo(emailUnico);
    }

    // 🔹 Test 2: Listar usuarios
    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = usuarioService.listar();
        assertThat(usuarios).isNotNull();
        assertThat(usuarios).isNotEmpty();
    }

    // 🔹 Test 3: Listar retos
    @Test
    void testListarRetos() {
        // Crear una empresa si no existe
        Usuario empresa = new Usuario();
        empresa.setNombre("Empresa Test");
        empresa.setEmail("empresa" + System.currentTimeMillis() + "@techreto.com");
        empresa.setPassword("empresa123");
        empresa.setRol("EMPRESA");
        usuarioService.guardar(empresa);

        // Si no hay retos, crea uno asociado a esa empresa
        if (retoService.listar().isEmpty()) {
            Reto nuevo = new Reto();
            nuevo.setTitulo("Reto de prueba");
            nuevo.setDescripcion("Descripción de prueba");
            nuevo.setTipo("INDIVIDUAL");
            nuevo.setEstado("ABIERTO");
            nuevo.setRecompensa("Certificado de prueba");
            nuevo.setEmpresa(empresa); // 👈 Campo obligatorio
            retoService.guardar(nuevo);
        }

        // Verificar
        List<Reto> retos = retoService.listar();
        assertThat(retos).isNotNull();
        assertThat(retos).isNotEmpty();
    }



    // 🔹 Test 4: Crear equipo
    @Test
    void testCrearEquipo() {
        Equipo equipo = new Equipo();
        equipo.setNombre("Equipo Test");
        equipo.setDescripcion("Equipo generado en test unitario");

        Equipo guardado = equipoService.guardar(equipo);
        assertThat(guardado).isNotNull();
        assertThat(guardado.getIdEquipo()).isNotNull();
    }

    // 🔹 Test 5: Crear notificación
    @Test
    void testCrearNotificacion() {
        // Asumiendo que hay al menos un usuario en la BD
        Usuario usuario = usuarioService.listar().get(0);

        Notificacion n = new Notificacion();
        n.setUsuario(usuario);
        n.setMensaje("Notificación de prueba unitaria");
        n.setLeida(false);

        Notificacion guardada = notificacionService.guardar(n);

        assertThat(guardada).isNotNull();
        assertThat(guardada.getIdNotificacion()).isNotNull();
        assertThat(guardada.getUsuario().getIdUsuario()).isEqualTo(usuario.getIdUsuario());
    }

    // 🔹 Test 6: Listar miembros de equipo
    @Test
    void testListarMiembros() {
        List<MiembroEquipo> miembros = miembroEquipoService.listar();
        assertThat(miembros).isNotNull();
        assertThat(miembros).isNotEmpty();
    }
}
