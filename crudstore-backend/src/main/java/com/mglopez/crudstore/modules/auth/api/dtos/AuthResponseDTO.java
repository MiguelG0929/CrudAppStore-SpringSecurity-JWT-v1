package com.mglopez.crudstore.modules.auth.api.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * ------------------------------------------------------------------
 * DTO DE RESPUESTA DE AUTENTICACIÓN (AuthResponseDTO)
 * ------------------------------------------------------------------
 * Este record representa la respuesta que se envía al cliente después de:
 *  - Login exitoso
 *  - Creación de un nuevo usuario
 *
 * Campos:
 *  - username: nombre del usuario autenticado
 *  - message: mensaje descriptivo de la operación (ej. "User created successfully")
 *  - jwt: token JWT generado para la sesión
 *  - status: indica si la operación fue exitosa (true/false)
 *
 * La anotación @JsonPropertyOrder garantiza que los campos se serialicen
 * en un orden consistente en la respuesta JSON.
 * ------------------------------------------------------------------
 */
@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDTO (
        String username,
        String message,
        String jwt,
        boolean status
) {}
