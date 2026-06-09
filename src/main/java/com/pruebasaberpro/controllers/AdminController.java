package com.pruebasaberpro.controllers;

import com.pruebasaberpro.models.Usuario;
import com.pruebasaberpro.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pruebasaberpro.models.Facultad;
import com.pruebasaberpro.models.Beneficio;
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final FacultadRepository facultadRepository;
    private final BeneficioRepository beneficioRepository;
    private final RolRepository rolRepository;

    public AdminController(UsuarioRepository usuarioRepository,
                           EstudianteRepository estudianteRepository,
                           FacultadRepository facultadRepository,
                           BeneficioRepository beneficioRepository,
                           RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.facultadRepository = facultadRepository;
        this.beneficioRepository = beneficioRepository;
        this.rolRepository = rolRepository;
    }

    private Usuario validarSesion(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuarioSesion");
        if (u == null || !u.getRol().getNombre().equals("ADMIN")) return null;
        return u;
    }

    // =================== DASHBOARD ===================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario",            usuario);
        model.addAttribute("totalDocentes",      usuarioRepository.countByRol_Id(3L));
        model.addAttribute("totalCoordinadores", usuarioRepository.countByRol_Id(2L));
        model.addAttribute("totalFacultades",    facultadRepository.count());
        model.addAttribute("totalBeneficios",    beneficioRepository.count());
        model.addAttribute("docentes",           usuarioRepository.findByRol_Id(3L));
        model.addAttribute("facultades",         facultadRepository.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/coordinadores")
    public String coordinadores(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        model.addAttribute("lista",   usuarioRepository.findByRol_Id(2L));
        model.addAttribute("nuevo",   new Usuario());
        return "admin/coordinadores";
    }

    @PostMapping("/coordinadores/guardar")
    public String guardarCoordinador(@RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String nombre,
                                     @RequestParam String correo,
                                     HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";

        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setRol(rolRepository.findByNombre("COORDINADOR"));
        u.setActivo(true);
        usuarioRepository.save(u);
        return "redirect:/admin/coordinadores";
    }

    @GetMapping("/coordinadores/eliminar/{id}")
    public String eliminarCoordinador(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        usuarioRepository.deleteById(id);
        return "redirect:/admin/coordinadores";
    }

    // =================== DOCENTES ===================
    @GetMapping("/docentes")
    public String docentes(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";
        model.addAttribute("usuario", usuario);
        model.addAttribute("lista",   usuarioRepository.findByRol_Id(3L));
        model.addAttribute("nuevo",   new Usuario());
        return "admin/docentes";
    }
    @PostMapping("/coordinadores/editar")
    public String editarCoordinador(@RequestParam Long id,
                                     @RequestParam String username,
                                     @RequestParam String password,
                                     @RequestParam String nombre,
                                     @RequestParam String correo,
                                     HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        Usuario u = usuarioRepository.findById(id).orElseThrow();
        u.setNombre(nombre);
        u.setUsername(username);
        u.setCorreo(correo);
        if (!password.isBlank()) u.setPassword(password);
        usuarioRepository.save(u);
        return "redirect:/admin/coordinadores";
    }

    @PostMapping("/docentes/guardar")
    public String guardarDocente(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String nombre,
                                 @RequestParam String correo,
                                 HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";

        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setRol(rolRepository.findByNombre("DOCENTE"));
        u.setActivo(true);
        usuarioRepository.save(u);
        return "redirect:/admin/docentes";
    }

    @GetMapping("/docentes/eliminar/{id}")
    public String eliminarDocente(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        usuarioRepository.deleteById(id);
        return "redirect:/admin/docentes";
    }
    @PostMapping("/docentes/editar")
    public String editarDocente(@RequestParam Long id,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String nombre,
                                 @RequestParam String correo,
                                 HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        Usuario u = usuarioRepository.findById(id).orElseThrow();
        u.setNombre(nombre);
        u.setUsername(username);
        u.setCorreo(correo);
        if (!password.isBlank()) u.setPassword(password);
        usuarioRepository.save(u);
        return "redirect:/admin/docentes";
    }

    // =================== FACULTADES ===================
    @GetMapping("/facultades")
    public String facultades(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("lista", facultadRepository.findAll());
        return "admin/facultades";
    }

    @PostMapping("/facultades/guardar")
    public String guardarFacultad(@RequestParam String nombre,
                                  @RequestParam String tipo,
                                  HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";

        com.pruebasaberpro.models.Facultad f = new com.pruebasaberpro.models.Facultad();
        f.setNombre(nombre);
        f.setTipo(tipo);
        facultadRepository.save(f);
        return "redirect:/admin/facultades";
    }

    @GetMapping("/facultades/eliminar/{id}")
    public String eliminarFacultad(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        facultadRepository.deleteById(id);
        return "redirect:/admin/facultades";
    }
    
    @PostMapping("/facultades/editar")
    public String editarFacultad(@RequestParam Long id,
                                  @RequestParam String nombre,
                                  @RequestParam String tipo,
                                  HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        Facultad f = facultadRepository.findById(id).orElseThrow();
        f.setNombre(nombre);
        f.setTipo(tipo);
        facultadRepository.save(f);
        return "redirect:/admin/facultades";
    }

    // =================== BENEFICIOS ===================
    @GetMapping("/beneficios")
    public String beneficios(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        model.addAttribute("lista", beneficioRepository.findAll());
        return "admin/beneficios";
    }

    @PostMapping("/beneficios/guardar")
    public String guardarBeneficio(@RequestParam String nombre,
                                   @RequestParam String descripcion,
                                   @RequestParam String tipoPrograma,
                                   @RequestParam String nivelFormacion,
                                   HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";

        com.pruebasaberpro.models.Beneficio b = new com.pruebasaberpro.models.Beneficio();
        b.setNombre(nombre);
        b.setDescripcion(descripcion);
        b.setTipoPrograma(tipoPrograma);
        b.setNivelFormacion(nivelFormacion);
        beneficioRepository.save(b);
        return "redirect:/admin/beneficios";
    }

    @GetMapping("/beneficios/eliminar/{id}")
    public String eliminarBeneficio(@PathVariable Long id, HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        beneficioRepository.deleteById(id);
        return "redirect:/admin/beneficios";
    }
    
    @PostMapping("/beneficios/editar")
    public String editarBeneficio(@RequestParam Long id,
                                   @RequestParam String nombre,
                                   @RequestParam String descripcion,
                                   @RequestParam String tipoPrograma,
                                   @RequestParam String nivelFormacion,
                                   HttpSession session) {
        if (validarSesion(session) == null) return "redirect:/login";
        Beneficio b = beneficioRepository.findById(id).orElseThrow();
        b.setNombre(nombre);
        b.setDescripcion(descripcion);
        b.setTipoPrograma(tipoPrograma);
        b.setNivelFormacion(nivelFormacion);
        beneficioRepository.save(b);
        return "redirect:/admin/beneficios";
    }
}