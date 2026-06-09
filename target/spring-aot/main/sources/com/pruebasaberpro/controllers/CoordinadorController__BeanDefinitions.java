package com.pruebasaberpro.controllers;

import com.pruebasaberpro.repositories.BeneficioRepository;
import com.pruebasaberpro.repositories.EstudianteRepository;
import com.pruebasaberpro.repositories.FacultadRepository;
import com.pruebasaberpro.repositories.ResultadoRepository;
import com.pruebasaberpro.repositories.RolRepository;
import com.pruebasaberpro.repositories.UsuarioRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CoordinadorController}.
 */
@Generated
public class CoordinadorController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'coordinadorController'.
   */
  private static BeanInstanceSupplier<CoordinadorController> getCoordinadorControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CoordinadorController>forConstructor(EstudianteRepository.class, ResultadoRepository.class, FacultadRepository.class, UsuarioRepository.class, RolRepository.class, BeneficioRepository.class)
            .withGenerator((registeredBean, args) -> new CoordinadorController(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'coordinadorController'.
   */
  public static BeanDefinition getCoordinadorControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CoordinadorController.class);
    beanDefinition.setInstanceSupplier(getCoordinadorControllerInstanceSupplier());
    return beanDefinition;
  }
}
