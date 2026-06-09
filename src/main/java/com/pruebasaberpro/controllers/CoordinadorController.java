package com.pruebasaberpro.controllers;

import com.pruebasaberpro.models.*;
import com.pruebasaberpro.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {

    private final EstudianteRepository estudianteRepository;
    private final ResultadoRepository resultadoRepository;
    private final FacultadRepository facultadRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BeneficioRepository beneficioRepository;
    private Double lecturaEscritura2; // Lectura Crítica
 // + getter y setter

    public CoordinadorController(EstudianteRepository estudianteRepository,
                                 ResultadoRepository resultadoRepository,
                                 FacultadRepository facultadRepository,
                                 UsuarioRepository usuarioRepository,
                                 RolRepository rolRepository,
                                 BeneficioRepository beneficioRepository) {
        this.estudianteRepository = estudianteRepository;
        this.resultadoRepository = resultadoRepository;
        this.facultadRepository = facultadRepository;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.beneficioRepository = beneficioRepository;
    }

    private Usuario validarSesion(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuarioSesion");
        if (u == null || !u.getRol().getNombre().equals("COORDINADOR")) return null;
        return u;
    }

    // =================== DASHBOARD ===================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        long total = estudianteRepository.count();
        long conResultados = resultadoRepository.findAll().stream()
            .map(r -> r.getEstudiante().getId()).distinct().count();
        long sinResultados = total - conResultados;

        model.addAttribute("usuario", usuario);
        model.addAttribute("totalEstudiantes", total);
        model.addAttribute("conResultados", conResultados);
        model.addAttribute("sinResultados", sinResultados);
        return "coordinador/dashboard";
    }

    // =================== CRUD ESTUDIANTES ===================
    @GetMapping("/estudiantes")
    public String estudiantes(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("lista", estudianteRepository.findAll());
        model.addAttribute("facultades", facultadRepository.findAll());
        return "coordinador/estudiantes";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@RequestParam String documento,
                                    @RequestParam String nombre,
                                    @RequestParam String apellido,
                                    @RequestParam String correo,
                                    @RequestParam String telefono,
                                    @RequestParam String tipoPrueba,
                                    @RequestParam Long facultadId,
                                    HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";

        Estudiante e = new Estudiante();
        e.setDocumento(documento);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setCorreo(correo);
        e.setTelefono(telefono);
        e.setTipoPrueba(tipoPrueba);
        e.setFacultad(facultadRepository.findById(facultadId).orElse(null));
        e.setAprobado(false);
        estudianteRepository.save(e);

        // Crear usuario para el estudiante
        Usuario u = new Usuario();
        u.setUsername(documento);
        u.setPassword(documento);
        u.setNombre(nombre + " " + apellido);
        u.setCorreo(correo);
        u.setRol(rolRepository.findByNombre("ESTUDIANTE"));
        u.setActivo(true);
        usuarioRepository.save(u);

        return "redirect:/coordinador/estudiantes";
    }

    @GetMapping("/estudiantes/aprobar/{id}")
    public String aprobarEstudiante(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        estudianteRepository.findById(id).ifPresent(e -> {
            e.setAprobado(true);
            estudianteRepository.save(e);
        });
        return "redirect:/coordinador/estudiantes";
    }

    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        estudianteRepository.deleteById(id);
        return "redirect:/coordinador/estudiantes";
    }

    @GetMapping("/estudiantes/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        model.addAttribute("facultades", facultadRepository.findAll());
        estudianteRepository.findById(id).ifPresent(e -> model.addAttribute("estudiante", e));
        return "coordinador/editar-estudiante";
    }

    @PostMapping("/estudiantes/actualizar")
    public String actualizarEstudiante(@RequestParam Long id,
                                       @RequestParam String nombre,
                                       @RequestParam String apellido,
                                       @RequestParam String correo,
                                       @RequestParam String telefono,
                                       @RequestParam String tipoPrueba,
                                       @RequestParam Long facultadId,
                                       HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        estudianteRepository.findById(id).ifPresent(e -> {
            e.setNombre(nombre);
            e.setApellido(apellido);
            e.setCorreo(correo);
            e.setTelefono(telefono);
            e.setTipoPrueba(tipoPrueba);
            e.setFacultad(facultadRepository.findById(facultadId).orElse(null));
            estudianteRepository.save(e);
        });
        return "redirect:/coordinador/estudiantes";
    }

    // =================== CALIFICAR (INGRESAR RESULTADO) ===================
    @GetMapping("/estudiantes/calificar/{id}")
    public String calificar(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        estudianteRepository.findById(id).ifPresent(e -> model.addAttribute("estudiante", e));
        return "coordinador/calificar";
    }

    @PostMapping("/estudiantes/calificar/guardar")
    public String guardarCalificacion(@RequestParam Long estudianteId,
                                      @RequestParam Double puntajeGlobal,
                                      @RequestParam Double lecturaEscritura,
                                      @RequestParam Double lecturaEscritura2,      // ← AGREGAR
                                      @RequestParam Double razonamientoCuantitativo,
                                      @RequestParam Double competenciasCiudadanas,
                                      @RequestParam Double inglesComponente,
                                      @RequestParam Double componenteEspecifico,
                                      @RequestParam String tipoPrueba,
                                      @RequestParam String fechaPrueba,
                                      HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";

        estudianteRepository.findById(estudianteId).ifPresent(e -> {
            Resultado r = new Resultado();
            r.setEstudiante(e);
            r.setPuntajeGlobal(puntajeGlobal);
            r.setLecturaEscritura(lecturaEscritura);   // ← AGREGAR (ver Paso 2)
            r.setRazonamientoCuantitativo(razonamientoCuantitativo);
            r.setCompetenciasCiudadanas(competenciasCiudadanas);
            r.setInglesComponente(inglesComponente);
            r.setComponenteEspecifico(componenteEspecifico);
            r.setTipoPrueba(tipoPrueba);
            r.setFechaPrueba(LocalDate.parse(fechaPrueba));
            resultadoRepository.save(r);
        });
        return "redirect:/coordinador/estudiantes";
    }

    // =================== INFORME GENERAL ===================
    @GetMapping("/informe-general")
    public String informeGeneral(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("resultados", resultadoRepository.findAll());
        return "coordinador/informe-general";
    }

    // =================== INFORME DETALLADO ===================
    @GetMapping("/informe-detallado")
    public String informeDetallado(@RequestParam(required = false) Long facultadId,
                                   HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        List<Resultado> resultados;
        if (facultadId != null) {
            resultados = resultadoRepository.findByEstudianteFacultadId(facultadId);
        } else {
            resultados = resultadoRepository.findAll();
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("resultados", resultados);
        model.addAttribute("facultades", facultadRepository.findAll());
        model.addAttribute("facultadSeleccionada", facultadId);
        return "coordinador/informe-detallado";
    }

    // =================== REVISAR PAGO ===================
    @GetMapping("/pago/revisar/{id}")
    public String revisarPago(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        estudianteRepository.findById(id).ifPresent(e -> model.addAttribute("estudiante", e));
        return "coordinador/revisar-pago";
    }

    @GetMapping("/pago/aprobar/{id}")
    public String aprobarPago(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        estudianteRepository.findById(id).ifPresent(e -> {
            e.setPagoAprobado(true);
            estudianteRepository.save(e);
        });
        return "redirect:/coordinador/estudiantes";
    }

    @GetMapping("/pago/rechazar/{id}")
    public String rechazarPago(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        estudianteRepository.findById(id).ifPresent(e -> {
            e.setPagoAprobado(false);
            estudianteRepository.save(e);
        });
        return "redirect:/coordinador/estudiantes";
    }

    // =================== INFORME BENEFICIOS ===================
    @GetMapping("/informe-beneficios")
    public String informeBeneficios(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("beneficios", beneficioRepository.findAll());
        model.addAttribute("resultados", resultadoRepository.findAll());
        return "coordinador/informe-beneficios";
    }
}