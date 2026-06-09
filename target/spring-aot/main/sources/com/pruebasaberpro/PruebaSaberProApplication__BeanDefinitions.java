package com.pruebasaberpro;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link PruebaSaberProApplication}.
 */
@Generated
public class PruebaSaberProApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'pruebaSaberProApplication'.
   */
  public static BeanDefinition getPruebaSaberProApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PruebaSaberProApplication.class);
    beanDefinition.setInstanceSupplier(PruebaSaberProApplication::new);
    return beanDefinition;
  }
}
