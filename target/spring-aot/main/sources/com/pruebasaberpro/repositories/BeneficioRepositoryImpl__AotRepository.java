package com.pruebasaberpro.repositories;

import com.pruebasaberpro.models.Beneficio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link BeneficioRepository}.
 */
@Generated
public class BeneficioRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public BeneficioRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link BeneficioRepository#findByNivelFormacion(java.lang.String)}.
   */
  public List<Beneficio> findByNivelFormacion(String nivelFormacion) {
    String queryString = "SELECT b FROM Beneficio b WHERE b.nivelFormacion = :nivelFormacion";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("nivelFormacion", nivelFormacion);

    return (List<Beneficio>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link BeneficioRepository#findByTipoPrograma(java.lang.String)}.
   */
  public List<Beneficio> findByTipoPrograma(String tipoPrograma) {
    String queryString = "SELECT b FROM Beneficio b WHERE b.tipoPrograma = :tipoPrograma";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("tipoPrograma", tipoPrograma);

    return (List<Beneficio>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link BeneficioRepository#findByTipoProgramaAndNivelFormacion(java.lang.String,java.lang.String)}.
   */
  public List<Beneficio> findByTipoProgramaAndNivelFormacion(String tipoPrograma,
      String nivelFormacion) {
    String queryString = "SELECT b FROM Beneficio b WHERE b.tipoPrograma = :tipoPrograma AND b.nivelFormacion = :nivelFormacion";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("tipoPrograma", tipoPrograma);
    query.setParameter("nivelFormacion", nivelFormacion);

    return (List<Beneficio>) query.getResultList();
  }
}
