package com.arka.arkahv.infraestructure.config;

import com.arka.arkahv.infraestructure.security.DetallesUsuario;
import com.arka.arkahv.infraestructure.security.ServicioDetallesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.WebApplicationContext;

@Configuration

@EnableMethodSecurity
public class ConfigSecurity {

    private ServicioDetallesUsuario servicioDetallesUsuario;

    @Autowired
    public void setServicioDetallesUsuario(ServicioDetallesUsuario servicioDetallesUsuario) {
        this.servicioDetallesUsuario = servicioDetallesUsuario;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(
                        "/auth/usuarios", "/auth/usuarios/inicioSesion", "/auth/usuarios/registro",
                        "/cotizaciones/**").permitAll()
                .anyRequest().authenticated();
        http.sessionManagement(
                sessionAuthenticationStrategy ->
                        sessionAuthenticationStrategy.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.csrf((csrf) -> csrf.disable()); //Cross-Site Request Forgery (CSRF) is an attack that forces authenticated users to submit a request to a Web application against which they are currently authenticated.

        //Pendiente filtro con JWT Token

        //http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); //Se agrega hasta el login

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(servicioDetallesUsuario);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean

    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public DetallesUsuario detallesUsuario() {
        return (DetallesUsuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }

}
