package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Estudiante;
import com.pruebasaberpro.models.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    List<Resultado> findByEstudiante(Estudiante estudiante);
    List<Resultado> findByEstudianteId(Long estudianteId);
    List<Resultado> findByEstudianteFacultadId(Long facultadId);
    List<Resultado> findByTipoPrueba(String tipoPrueba);
}