package com.pruebasaberpro.controllers;

import com.pruebasaberpro.models.Usuario;
import com.pruebasaberpro.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        Map<Long, String> colores = new HashMap<>();
        for (Usuario u : usuarios) {
            String color = switch (u.getRol().getNombre()) {
                case "ADMIN"       -> "linear-gradient(135deg,#ef4444,#991b1b)";
                case "COORDINADOR" -> "linear-gradient(135deg,#2563eb,#1d4ed8)";
                case "DOCENTE"     -> "linear-gradient(135deg,#10b981,#047857)";
                default            -> "linear-gradient(135deg,#f59e0b,#b45309)";
            };
            colores.put(u.getId(), color);
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("colores", colores);
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null || !usuario.getPassword().equals(password)) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "login";
        }

        if (!usuario.isActivo()) {
            model.addAttribute("error", "Tu cuenta está inactiva");
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "login";
        }

        session.setAttribute("usuarioSesion", usuario);
        session.setAttribute("rolSesion", usuario.getRol().getNombre());

        return switch (usuario.getRol().getNombre()) {
            case "ADMIN"       -> "redirect:/admin/dashboard";
            case "COORDINADOR" -> "redirect:/coordinador/dashboard";
            case "DOCENTE"     -> "redirect:/docente/dashboard";
            case "ESTUDIANTE"  -> "redirect:/estudiante/dashboard";
            default            -> "redirect:/login?error";
        };
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}