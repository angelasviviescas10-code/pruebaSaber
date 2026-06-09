package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Estudiante;
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
 * AOT generated JPA repository implementation for {@link EstudianteRepository}.
 */
@Generated
public class EstudianteRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public EstudianteRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByAprobado(boolean)}.
   */
  public List<Estudiante> findByAprobado(boolean aprobado) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.aprobado = :aprobado";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("aprobado", aprobado);

    return (List<Estudiante>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByCorreo(java.lang.String)}.
   */
  public Optional<Estudiante> findByCorreo(String correo) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.correo = :correo";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("correo", correo);

    return Optional.ofNullable((Estudiante) convertOne(query.getSingleResultOrNull(), false, Estudiante.class));
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByDocumento(java.lang.String)}.
   */
  public Optional<Estudiante> findByDocumento(String documento) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.documento = :documento";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("documento", documento);

    return Optional.ofNullable((Estudiante) convertOne(query.getSingleResultOrNull(), false, Estudiante.class));
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByFacultadId(java.lang.Long)}.
   */
  public List<Estudiante> findByFacultadId(Long facultadId) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.facultad.id = :facultadId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("facultadId", facultadId);

    return (List<Estudiante>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByFacultadIdAndAprobado(java.lang.Long,boolean)}.
   */
  public List<Estudiante> findByFacultadIdAndAprobado(Long facultadId, boolean aprobado) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.facultad.id = :facultadId AND e.aprobado = :aprobado";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("facultadId", facultadId);
    query.setParameter("aprobado", aprobado);

    return (List<Estudiante>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByFacultadIdAndTipoPrueba(java.lang.Long,java.lang.String)}.
   */
  public List<Estudiante> findByFacultadIdAndTipoPrueba(Long facultadId, String tipoPrueba) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.facultad.id = :facultadId AND e.tipoPrueba = :tipoPrueba";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("facultadId", facultadId);
    query.setParameter("tipoPrueba", tipoPrueba);

    return (List<Estudiante>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link EstudianteRepository#findByTipoPrueba(java.lang.String)}.
   */
  public List<Estudiante> findByTipoPrueba(String tipoPrueba) {
    String queryString = "SELECT e FROM Estudiante e WHERE e.tipoPrueba = :tipoPrueba";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("tipoPrueba", tipoPrueba);

    return (List<Estudiante>) query.getResultList();
  }
}
