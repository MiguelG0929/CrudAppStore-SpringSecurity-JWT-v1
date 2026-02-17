package com.mglopez.crudstore.modules.auth.infrastructure.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mglopez.crudstore.modules.auth.infrastructure.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

/**
 * ------------------------------------------------------------------
 * FILTRO JWT (JWTokenValidator)
 * ------------------------------------------------------------------
 * Este filtro se ejecuta una vez por petición (extends OncePerRequestFilter)
 * y tiene la responsabilidad de:
 *  - Interceptar las solicitudes HTTP entrantes
 *  - Validar el token JWT si está presente en el header Authorization
 *  - Extraer información del usuario (username y authorities)
 *  - Configurar el contexto de seguridad de Spring Security (SecurityContext)
 *
 * Si el token es inválido o no existe, la petición continúa sin autenticación
 * y los endpoints que requieran autenticación fallarán automáticamente.
 *
 * NOTA:
 * - JWT esperado en el header Authorization con el prefijo "Bearer "
 * - Authorities se extraen del claim "authorities" y se convierten en GrantedAuthority
 *
 * ------------------------------------------------------------------
 */
public class JWTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils; // Utilidad para validar y extraer info del JWT

    public JWTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Método principal del filtro
     * @param request Petición HTTP entrante
     * @param response Respuesta HTTP
     * @param filterChain Cadena de filtros de Spring Security
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener header Authorization
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // ✅ Si no hay token o no comienza con "Bearer ", continuar sin autenticar
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extraer token JWT del header
            String jwtToken = header.substring(7);

            // Validar token y obtener DecodedJWT
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            // Extraer username del token
            String username = jwtUtils.extractUsername(decodedJWT);

            // Extraer authorities (roles y permisos) del claim "authorities"
            String stringAuthorities =
                    jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            Collection<? extends GrantedAuthority> authorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

            // Crear objeto Authentication y setearlo en el contexto de seguridad
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null, // no se almacena password en context
                            authorities
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            // ✅ Si el token es inválido o hubo error, limpiar contexto
            SecurityContextHolder.clearContext();
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
