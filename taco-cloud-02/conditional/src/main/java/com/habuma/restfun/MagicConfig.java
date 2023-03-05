package com.habuma.restfun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicConfig {

  @Bean
  @Conditional(MagicExistsCondition.class) // 只在某个条件下（后面的参数值）才实例化Bean
  public MagicBean magicBean() {
    return new MagicBean();
  }
  
}