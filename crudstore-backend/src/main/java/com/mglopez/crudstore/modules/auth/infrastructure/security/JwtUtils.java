package com.mglopez.crudstore.modules.auth.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ------------------------------------------------------------------
 * UTILIDAD JWT (JwtUtils)
 * ------------------------------------------------------------------
 * Clase responsable de:
 *  - Crear tokens JWT válidos para la autenticación de usuarios
 *  - Validar tokens entrantes
 *  - Extraer información (claims) de los tokens
 *
 * Los tokens incluyen:
 *  - username (subject)
 *  - authorities (roles y permisos)
 *  - issuer (emisor)
 *  - fecha de emisión y expiración
 *  - JWTId único
 *
 * NOTA:
 *  - La clave privada para firmar el token se inyecta desde application.properties
 *  - Se utiliza algoritmo HMAC256 para firmar el token
 *  - Tiempo de expiración configurado: 30 minutos
 *
 * ------------------------------------------------------------------
 */
@Component
public class JwtUtils {

    // Clave secreta para firmar y validar tokens (HMAC256)
    @Value("${security.jwt.key.private}")
    private String privateKey;

    // Identificador del generador de tokens (issuer)
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    /**
     * Crea un token JWT a partir de la información de Authentication
     * @param authentication objeto de Spring Security con usuario y authorities
     * @return token JWT firmado en formato String
     */
    public String createToken(Authentication authentication)
    {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        // Extraer username del Authentication
        String username = authentication.getPrincipal().toString();

        // Extraer roles/permissions y unirlos en un String separado por comas
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Construir token JWT
        String jwtToken =  JWT.create()
                .withIssuer(this.userGenerator) // emisor
                .withSubject(username) // usuario
                .withClaim("authorities", authorities) // roles/permisos
                .withIssuedAt(new Date()) // fecha de emisión
                .withExpiresAt(new Date(System.currentTimeMillis()+1800000))  // expiración 30 min
                .withJWTId(UUID.randomUUID().toString()) // ID único del token
                .withNotBefore(new Date(System.currentTimeMillis())) // válido desde ahora
                .sign(algorithm); // firmar con HMAC256

        return  jwtToken;
    }

    /**
     * Valida un token JWT
     * @param token JWT en formato String
     * @return DecodedJWT si es válido
     * @throws JWTVerificationException si el token es inválido
     */
    public DecodedJWT validateToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            // Verificar token y decodificar
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        }
        catch (JWTVerificationException exception)
        {
            // Lanza excepción si el token no es válido
            throw new JWTVerificationException("Token inválido. No autorizado");
        }
    }

    /**
     * Extrae el username (subject) de un DecodedJWT
     * @param decodedJWT token decodificado
     * @return username del token
     */
    public String extractUsername(DecodedJWT decodedJWT)
    {
        return decodedJWT.getSubject();
    }

    /**
     * Obtiene un claim específico del token
     * @param decodedJWT token decodificado
     * @param claimName nombre del claim
     * @return Claim solicitado
     */
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName)
    {
        return decodedJWT.getClaim(claimName);
    }

    /**
     * Retorna todos los claims del token
     * @param decodedJWT token decodificado
     * @return mapa con todos los claims
     */
    public Map<String , Claim> returnAllClaims(DecodedJWT decodedJWT)
    {
        return decodedJWT.getClaims();
    }
}
