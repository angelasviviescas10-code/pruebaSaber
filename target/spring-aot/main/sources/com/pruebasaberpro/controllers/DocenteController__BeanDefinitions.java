package com.pruebasaberpro.controllers;

import com.pruebasaberpro.repositories.BeneficioRepository;
import com.pruebasaberpro.repositories.EstudianteRepository;
import com.pruebasaberpro.repositories.FacultadRepository;
import com.pruebasaberpro.repositories.ResultadoRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DocenteController}.
 */
@Generated
public class DocenteController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'docenteController'.
   */
  private static BeanInstanceSupplier<DocenteController> getDocenteControllerInstanceSupplier() {
    return BeanInstanceSupplier.<DocenteController>forConstructor(EstudianteRepository.class, ResultadoRepository.class, FacultadRepository.class, BeneficioRepository.class)
            .withGenerator((registeredBean, args) -> new DocenteController(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'docenteController'.
   */
  public static BeanDefinition getDocenteControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DocenteController.class);
    beanDefinition.setInstanceSupplier(getDocenteControllerInstanceSupplier());
    return beanDefinition;
  }
}
