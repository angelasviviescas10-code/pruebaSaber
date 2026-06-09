package com.pruebasaberpro.controllers;

import com.pruebasaberpro.repositories.BeneficioRepository;
import com.pruebasaberpro.repositories.EstudianteRepository;
import com.pruebasaberpro.repositories.FacultadRepository;
import com.pruebasaberpro.repositories.RolRepository;
import com.pruebasaberpro.repositories.UsuarioRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AdminController}.
 */
@Generated
public class AdminController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'adminController'.
   */
  private static BeanInstanceSupplier<AdminController> getAdminControllerInstanceSupplier() {
    return BeanInstanceSupplier.<AdminController>forConstructor(UsuarioRepository.class, EstudianteRepository.class, FacultadRepository.class, BeneficioRepository.class, RolRepository.class)
            .withGenerator((registeredBean, args) -> new AdminController(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'adminController'.
   */
  public static BeanDefinition getAdminControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AdminController.class);
    beanDefinition.setInstanceSupplier(getAdminControllerInstanceSupplier());
    return beanDefinition;
  }
}
