package com.pruebasaberpro.controllers;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.pruebasaberpro.models.*;
import com.pruebasaberpro.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;
    private final ResultadoRepository resultadoRepository;
    private final BeneficioRepository beneficioRepository;

    public EstudianteController(EstudianteRepository estudianteRepository,
                                ResultadoRepository resultadoRepository,
                                BeneficioRepository beneficioRepository) {
        this.estudianteRepository = estudianteRepository;
        this.resultadoRepository = resultadoRepository;
        this.beneficioRepository = beneficioRepository;
    }

    private Usuario validarSesion(HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuarioSesion");
        if (u == null || !u.getRol().getNombre().equals("ESTUDIANTE")) return null;
        return u;
    }

    private Optional<Estudiante> getEstudiante(Usuario usuario) {
        return estudianteRepository.findAll().stream()
            .filter(e -> e.getCorreo().equals(usuario.getCorreo()))
            .findFirst();
    }

    // =================== DASHBOARD ===================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        Optional<Estudiante> opt = getEstudiante(usuario);
        if (opt.isPresent()) {
            Estudiante e = opt.get();
            model.addAttribute("estudiante", e);
            List<Resultado> resultados = resultadoRepository.findByEstudianteId(e.getId());
            if (!resultados.isEmpty()) {
                model.addAttribute("ultimoResultado", resultados.get(resultados.size() - 1));
            }
        }
        return "estudiante/dashboard";
    }

    // =================== DATOS PERSONALES ===================
    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        getEstudiante(usuario).ifPresent(e -> model.addAttribute("estudiante", e));
        return "estudiante/perfil";
    }

    // =================== ULTIMO RESULTADO ===================
    @GetMapping("/ultimo-resultado")
    public String ultimoResultado(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        getEstudiante(usuario).ifPresent(e -> {
            model.addAttribute("estudiante", e);
            List<Resultado> resultados = resultadoRepository.findByEstudianteId(e.getId());
            if (!resultados.isEmpty()) {
                model.addAttribute("resultado", resultados.get(resultados.size() - 1));
            }
        });
        return "estudiante/ultimo-resultado";
    }

    // =================== TODOS LOS RESULTADOS ===================
    @GetMapping("/resultados")
    public String resultados(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        getEstudiante(usuario).ifPresent(e -> {
            model.addAttribute("estudiante", e);
            model.addAttribute("resultados", resultadoRepository.findByEstudianteId(e.getId()));
        });
        return "estudiante/resultados";
    }

    // =================== BENEFICIOS ===================
    @GetMapping("/beneficios")
    public String beneficios(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);
        getEstudiante(usuario).ifPresent(e -> {
            model.addAttribute("estudiante", e);
            String tipo = e.getFacultad().getTipo();
            String nivel = e.getTipoPrueba().equals("PRO") ? "PROFESIONAL" : "TECNOLOGO";
            model.addAttribute("beneficios",
                beneficioRepository.findByTipoProgramaAndNivelFormacion(tipo, nivel));
        });
        return "estudiante/beneficios";
    }
    
    // =================== CARGAR PAGO ===================
    @GetMapping("/pago")
    public String pago(HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        model.addAttribute("usuario", usuario);

        Optional<Estudiante> opt = getEstudiante(usuario);
        System.out.println(">>> [PAGO GET] Usuario correo: " + usuario.getCorreo());
        System.out.println(">>> [PAGO GET] Estudiante encontrado: " + opt.isPresent());
        opt.ifPresent(e -> {
            System.out.println(">>> [PAGO GET] rutaPago: " + e.getRutaPago());
            model.addAttribute("estudiante", e);
        });

        return "estudiante/pago";
    }

    @PostMapping("/pago/subir")
    public String subirPago(@RequestParam("archivo") MultipartFile archivo,
                            HttpSession session, Model model) {
        Usuario usuario = validarSesion(session);
        if (usuario == null) return "redirect:/login";

        Optional<Estudiante> opt = getEstudiante(usuario);
        System.out.println(">>> [PAGO POST] Usuario correo: " + usuario.getCorreo());
        System.out.println(">>> [PAGO POST] Estudiante encontrado: " + opt.isPresent());
        System.out.println(">>> [PAGO POST] Archivo recibido: " + (archivo != null ? archivo.getOriginalFilename() : "NULL"));
        System.out.println(">>> [PAGO POST] Archivo vacío: " + (archivo != null && archivo.isEmpty()));

        if (opt.isPresent()) {
            try {
                String carpeta = "uploads/pagos/";
                new File(carpeta).mkdirs();
                String nombreArchivo = usuario.getUsername() + "_" + archivo.getOriginalFilename();
                Files.write(Paths.get(carpeta + nombreArchivo), archivo.getBytes());
                Estudiante e = opt.get();
                e.setRutaPago(nombreArchivo);
                estudianteRepository.save(e);
                System.out.println(">>> [PAGO POST] Guardado OK: " + nombreArchivo);
                model.addAttribute("exito", "Comprobante subido correctamente");
            } catch (Exception ex) {
                System.out.println(">>> [PAGO POST] ERROR: " + ex.getMessage());
                ex.printStackTrace();
                model.addAttribute("error", "Error al subir el archivo: " + ex.getMessage());
            }
        }

        model.addAttribute("usuario", usuario);
        opt.ifPresent(e -> model.addAttribute("estudiante", e));
        return "estudiante/pago";
    }
}