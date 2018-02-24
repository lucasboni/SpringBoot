package br.com.lucas.boni.bittencourt.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    public static final String[] PUBLIC_MATCHERS = {
           "/h2-console/**",
    };

    public static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){//se for teste
            http.headers().frameOptions().disable();                //livera acesso por conta do h2
        }
        http.cors() // como corsConfigurationSource esta implementado chama ele
        .and().csrf().disable();//como a aplicação nao guarda sesão desabilito a protecao a esse tipo de ataque
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()// permite todos da lista somente no método GET
                .antMatchers(PUBLIC_MATCHERS).permitAll() // permite todos da lista
                .anyRequest().authenticated();            // para todo resto requer autenticação
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//assegura que noa cria sessao
    }


    /**
     * Copiei de https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
     * permite o acesso dos endpoints om as configuracoes basicas
     * isso habilita requisições de multiplas fontes
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
