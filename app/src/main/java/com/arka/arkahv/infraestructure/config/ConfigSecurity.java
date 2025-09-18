package com.arka.arkahv.infraestructure.config;

import com.arka.arkahv.infraestructure.security.DetallesUsuario;
import com.arka.arkahv.infraestructure.security.JwtFiltroPeticion;
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
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
                        "/auth/usuarios", "/auth/usuarios/inicioSesion", "/auth/usuarios/registro").permitAll()
                .anyRequest().authenticated();
        http.sessionManagement(
                sessionAuthenticationStrategy ->
                        sessionAuthenticationStrategy.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.csrf((csrf) -> csrf.disable());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public JwtFiltroPeticion authenticationJwtTokenFilter() {
        return new JwtFiltroPeticion();
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
