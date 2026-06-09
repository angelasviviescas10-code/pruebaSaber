package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Examen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link ExamenRepository}.
 */
@Generated
public class ExamenRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ExamenRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ExamenRepository#findByEstado(java.lang.String)}.
   */
  public List<Examen> findByEstado(String estado) {
    String queryString = "SELECT e FROM Examen e WHERE e.estado = :estado";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("estado", estado);

    return (List<Examen>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ExamenRepository#findByEstudianteDocumento(java.lang.String)}.
   */
  public Optional<Examen> findByEstudianteDocumento(String documento) {
    String queryString = "SELECT e FROM Examen e LEFT JOIN e.estudiante e_0 WHERE e_0.documento = :documento";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("documento", documento);

    return Optional.ofNullable((Examen) convertOne(query.getSingleResultOrNull(), false, Examen.class));
  }

  /**
   * AOT generated implementation of {@link ExamenRepository#findByEstudianteFacultadId(java.lang.Long)}.
   */
  public List<Examen> findByEstudianteFacultadId(Long facultadId) {
    String queryString = "SELECT e FROM Examen e LEFT JOIN e.estudiante e_0 LEFT JOIN e_0.facultad f WHERE f.id = :facultadId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("facultadId", facultadId);

    return (List<Examen>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ExamenRepository#findByEstudianteId(java.lang.Long)}.
   */
  public List<Examen> findByEstudianteId(Long estudianteId) {
    String queryString = "SELECT e FROM Examen e WHERE e.estudiante.id = :estudianteId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("estudianteId", estudianteId);

    return (List<Examen>) query.getResultList();
  }
}
