package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //下面注释打开即表示使用https
//                .requiresChannel()
//                .antMatchers("/**").requiresSecure()
//                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .tokenRepository(new InMemoryTokenRepositoryImpl())
                .tokenValiditySeconds(2419200)
                .key("spittrKey")
                .and()
                .httpBasic()
                .realmName("Spittr")
                .and()
                .authorizeRequests()
                .antMatchers("/").hasAnyRole("USER")
                .antMatchers("/spitter/me").authenticated()
                .antMatchers("/spittles").authenticated()
                .anyRequest().permitAll()
//                .and()
//                .requiresChannel()
//                .antMatchers("/spitter/register").requiresSecure()
//    .and().csrf().disable()
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//      .inMemoryAuthentication()
//        .withUser("user").password("password").roles("USER");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password,true from Spitter where username=? ")
                .authoritiesByUsernameQuery(
                        "select username,'ROLE_USER' from Spitter where username=?"
                );
    }


}
