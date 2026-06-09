package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Estudiante;
import com.pruebasaberpro.models.Resultado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link ResultadoRepository}.
 */
@Generated
public class ResultadoRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ResultadoRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ResultadoRepository#findByEstudiante(com.pruebasaberpro.models.Estudiante)}.
   */
  public List<Resultado> findByEstudiante(Estudiante estudiante) {
    String queryString = "SELECT r FROM Resultado r WHERE r.estudiante = :estudiante";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("estudiante", estudiante);

    return (List<Resultado>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ResultadoRepository#findByEstudianteFacultadId(java.lang.Long)}.
   */
  public List<Resultado> findByEstudianteFacultadId(Long facultadId) {
    String queryString = "SELECT r FROM Resultado r LEFT JOIN r.estudiante e LEFT JOIN e.facultad f WHERE f.id = :facultadId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("facultadId", facultadId);

    return (List<Resultado>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ResultadoRepository#findByEstudianteId(java.lang.Long)}.
   */
  public List<Resultado> findByEstudianteId(Long estudianteId) {
    String queryString = "SELECT r FROM Resultado r WHERE r.estudiante.id = :estudianteId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("estudianteId", estudianteId);

    return (List<Resultado>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ResultadoRepository#findByTipoPrueba(java.lang.String)}.
   */
  public List<Resultado> findByTipoPrueba(String tipoPrueba) {
    String queryString = "SELECT r FROM Resultado r WHERE r.tipoPrueba = :tipoPrueba";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("tipoPrueba", tipoPrueba);

    return (List<Resultado>) query.getResultList();
  }
}
