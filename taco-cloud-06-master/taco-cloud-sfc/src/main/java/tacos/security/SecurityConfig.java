package tacos.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import tacos.User;
import tacos.data.UserRepository;

@Configuration
public class SecurityConfig {
  
  @Bean
  public PasswordEncoder passwordEncoder() { //密码加密
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepo) { //得到用户信息，会被加入spring上下文，同时自动做登录验证
    return username -> {
      User user = userRepo.findByUsername(username);
      if (user != null) {
        return user;
      }
      throw new UsernameNotFoundException(
                      "User '" + username + "' not found");
    };
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //过滤器，鉴权、认证的工作，对不同的URL进行不同的权限控制
    return http
      .authorizeRequests()
        .mvcMatchers("/design", "/orders").hasRole("USER")
        .anyRequest().permitAll()

      .and()
        .formLogin()
          .loginPage("/login")
          
      .and()
        .logout()
          .logoutSuccessUrl("/")
          
      // Make H2-Console non-secured; for debug purposes
      .and()
        .csrf()
          .ignoringAntMatchers("/h2-console/**")
  
      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
      .and()  
        .headers()
          .frameOptions()
            .sameOrigin()
            
       .and()
       .build();
  }
  
}
