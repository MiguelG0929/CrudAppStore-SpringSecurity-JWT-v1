package com.mglopez.crudstore.modules.auth.api.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * ------------------------------------------------------------------
 * DTO DE ENTRADA PARA LOGIN (AuthLoginRequestDTO)
 * ------------------------------------------------------------------
 * Este record representa la información que el cliente debe enviar
 * al endpoint de login para autenticarse.
 *
 * Campos:
 *  - username: nombre de usuario que intenta iniciar sesión (no puede estar vacío)
 *  - password: contraseña correspondiente al usuario (no puede estar vacío)
 *
 * Las anotaciones @NotBlank aseguran que estos campos sean obligatorios
 * y no nulos ni vacíos, activando validación automática de Spring.
 *
 * Se utiliza en conjunto con UserDetailsServiceImpl para autenticar
 * al usuario y generar el JWT correspondiente.
 * ------------------------------------------------------------------
 */
public record AuthLoginRequestDTO (
        @NotBlank String username,
        @NotBlank String password
) {}
