package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link FacultadRepository}.
 */
@Generated
public class FacultadRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public FacultadRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link FacultadRepository#findByTipo(java.lang.String)}.
   */
  public List<Facultad> findByTipo(String tipo) {
    String queryString = "SELECT f FROM Facultad f WHERE f.tipo = :tipo";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("tipo", tipo);

    return (List<Facultad>) query.getResultList();
  }
}
