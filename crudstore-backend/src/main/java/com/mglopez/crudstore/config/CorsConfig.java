package com.mglopez.crudstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ------------------------------------------------------------------
 * CONFIGURACIÓN DE CORS (Cross-Origin Resource Sharing)
 * ------------------------------------------------------------------
 * Este componente permite que el frontend (por ejemplo Angular en localhost:4200)
 * pueda hacer peticiones HTTP al backend sin restricciones de origen.
 *
 * Propósito:
 *  - Permitir comunicación entre frontend y backend en desarrollo.
 *  - Evitar errores de CORS (Cross-Origin Request Blocked) en el navegador.
 *
 * Configuración:
 *  - Se permiten todos los headers.
 *  - Se permiten los métodos GET, POST, PUT, DELETE y OPTIONS.
 *  - Se habilita el envío de cookies/credenciales.
 *  - Solo se aplica a rutas que empiecen con /api/**
 * ------------------------------------------------------------------
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Método para registrar los mappings de CORS.
             *
             * @param registry objeto CorsRegistry para configurar los orígenes, métodos y headers permitidos
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Solo rutas que comiencen con /api/
                        .allowedOrigins("http://localhost:4200") // Origen permitido (frontend)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Todos los headers permitidos
                        .allowCredentials(true); // Permitir cookies y credenciales
            }
        };
    }

}
