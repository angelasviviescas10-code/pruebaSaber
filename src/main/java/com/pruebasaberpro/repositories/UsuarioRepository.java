package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    List<Usuario> findByRol_Id(Long rolId);

    long countByRol_Id(Long rolId);
}