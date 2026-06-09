package com.pruebasaberpro.controllers;

import com.pruebasaberpro.models.*;
import com.pruebasaberpro.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/docente")
public class DocenteController {

    private final EstudianteRepository estudianteRepository;
    private final ResultadoRepository resultadoRepository;
    private final FacultadRepository facultadRepository;
    private final BeneficioRepository beneficioRepository;

    public DocenteController(EstudianteRepository estudianteRepository,
                             ResultadoRepository resultadoRepository,
                             FacultadRepository facultadRepository,
                             BeneficioRepository beneficioRepository) {
        this.estudianteRepository = estudianteRepository;
        this.resultadoRepository = resultadoRepository;
        this.facultadRepository = facultadRepository;
        this.beneficioRepository = beneficioRepository;
    }

    private Usuario validarSesion(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuarioSesion");
        if (u == null || !u.getRol().getNombre().equals("DOCENTE")) return null;
        return u;
    }

    // =================== DASHBOARD ===================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("facultades", facultadRepository.findAll());
        return "docente/dashboard";
    }

    // =================== BUSCAR POR FACULTAD ===================
    @GetMapping("/buscar-facultad")
    public String buscarPorFacultad(@RequestParam(required = false) Long facultadId,
                                    HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        List<Estudiante> estudiantes = null;
        if (facultadId != null) {
            estudiantes = estudianteRepository.findByFacultadId(facultadId);
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("facultades", facultadRepository.findAll());
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("facultadSeleccionada", facultadId);
        return "docente/buscar-facultad";
    }

    // =================== BUSCAR POR CÉDULA ===================
    @GetMapping("/buscar-cedula")
    public String buscarPorCedula(@RequestParam(required = false) String documento,
                                  HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        Estudiante estudiante = null;
        if (documento != null && !documento.isBlank()) {
            estudiante = estudianteRepository.findByDocumento(documento).orElse(null);
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("documento", documento);
        return "docente/buscar-cedula";
    }

    // =================== INFORME DE ALUMNOS (TOTAL Y ÚNICO) ===================
    @GetMapping("/informe-alumnos")
    public String informeAlumnos(@RequestParam(required = false) Long facultadId,
                                 HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        List<Resultado> resultados;
        if (facultadId != null) {
            // Único: filtrado por facultad
            resultados = resultadoRepository.findByEstudianteFacultadId(facultadId);
        } else {
            // Total: todos
            resultados = resultadoRepository.findAll();
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("resultados", resultados);
        model.addAttribute("facultades", facultadRepository.findAll());
        model.addAttribute("facultadSeleccionada", facultadId);
        return "docente/informe-alumnos";
    }

    // =================== INFORME DE BENEFICIOS ===================
    @GetMapping("/informe-beneficios")
    public String informeBeneficios(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("beneficios", beneficioRepository.findAll());
        model.addAttribute("resultados", resultadoRepository.findAll());
        return "docente/informe-beneficios";
    }

    // =================== RESOLUCIÓN BENEFICIOS ===================
    @GetMapping("/resolucion-beneficios")
    public String resolucionBeneficios(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("beneficios", beneficioRepository.findAll());
        return "docente/resolucion-beneficios";
    }
}