package com.pruebasaberpro.controllers;

import com.pruebasaberpro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/api/usuarios/quick")
    public List<Map<String, String>> quick() {
        return usuarioRepository.findAll().stream()
            .map(u -> {
                Map<String, String> m = new HashMap<>();
                m.put("username",     u.getUsername());
                m.put("display_name", u.getNombre());
                m.put("rol",          "usuario");
                return m;
            })
            .collect(Collectors.toList());
    }
}