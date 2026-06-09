package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultadRepository extends JpaRepository<Facultad, Long> {
    List<Facultad> findByTipo(String tipo);
}