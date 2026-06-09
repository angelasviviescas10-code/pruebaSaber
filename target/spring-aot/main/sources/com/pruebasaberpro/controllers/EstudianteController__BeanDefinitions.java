package com.pruebasaberpro.controllers;

import com.pruebasaberpro.repositories.BeneficioRepository;
import com.pruebasaberpro.repositories.EstudianteRepository;
import com.pruebasaberpro.repositories.ResultadoRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link EstudianteController}.
 */
@Generated
public class EstudianteController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'estudianteController'.
   */
  private static BeanInstanceSupplier<EstudianteController> getEstudianteControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EstudianteController>forConstructor(EstudianteRepository.class, ResultadoRepository.class, BeneficioRepository.class)
            .withGenerator((registeredBean, args) -> new EstudianteController(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'estudianteController'.
   */
  public static BeanDefinition getEstudianteControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EstudianteController.class);
    beanDefinition.setInstanceSupplier(getEstudianteControllerInstanceSupplier());
    return beanDefinition;
  }
}
