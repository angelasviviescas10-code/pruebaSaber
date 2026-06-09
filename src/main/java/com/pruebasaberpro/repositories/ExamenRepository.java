package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    Optional<Examen> findByEstudianteDocumento(String documento);

    List<Examen> findByEstado(String estado);

    List<Examen> findByEstudianteFacultadId(Long facultadId);

    // Necesario para EstudianteController
    List<Examen> findByEstudianteId(Long estudianteId);
}