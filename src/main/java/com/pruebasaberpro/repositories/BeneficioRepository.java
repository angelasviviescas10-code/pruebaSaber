package com.pruebasaberpro.repositories;


import com.pruebasaberpro.models.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {
    List<Beneficio> findByTipoPrograma(String tipoPrograma);
    List<Beneficio> findByNivelFormacion(String nivelFormacion);
    List<Beneficio> findByTipoProgramaAndNivelFormacion(String tipoPrograma, String nivelFormacion);
}