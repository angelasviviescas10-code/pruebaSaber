package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByCorreo(String correo);

    Optional<Estudiante> findByDocumento(String documento);

    List<Estudiante> findByFacultadId(Long facultadId);

    List<Estudiante> findByAprobado(boolean aprobado);

    List<Estudiante> findByTipoPrueba(String tipoPrueba);

    List<Estudiante> findByFacultadIdAndTipoPrueba(Long facultadId, String tipoPrueba);

    List<Estudiante> findByFacultadIdAndAprobado(Long facultadId, boolean aprobado);
}