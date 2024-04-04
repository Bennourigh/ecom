package org.mgb.customerservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .headers(h->h.frameOptions(fo->fo.disable()))
                .authorizeHttpRequests(ar->ar.requestMatchers("/**").permitAll())
                .authorizeHttpRequests(ar->ar.requestMatchers("/localhost:4200/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/h2-console/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/api/customers/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/api/inventory-service/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/order-service/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/api/products/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/api/users/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/swagger-resources/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/swagger-ui.html").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**").permitAll())
                //.authorizeHttpRequests(ar->ar.requestMatchers("/v2/api-docs").permitAll())
                //.authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                //.oauth2ResourceServer(o2->o2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
