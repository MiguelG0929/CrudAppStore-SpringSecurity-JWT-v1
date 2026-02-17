package com.mglopez.crudstore.modules.auth.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * ------------------------------------------------------------------
 * DTO DE ENTRADA PARA CREACIÓN DE USUARIOS (AuthCreateUserDTO)
 * ------------------------------------------------------------------
 * Este record representa la información que el cliente debe enviar
 * al endpoint de creación de un nuevo usuario.
 *
 * Campos:
 *  - username: nombre del nuevo usuario (obligatorio, no vacío)
 *  - password: contraseña para el nuevo usuario (obligatorio, no vacío)
 *  - roleRequest: objeto que contiene la lista de roles asignados al usuario
 *                 (validado con @Valid)
 *
 * Validaciones:
 *  - @NotBlank asegura que username y password no sean nulos ni vacíos
 *  - @Valid propaga la validación hacia el objeto roleRequest
 *
 * Uso:
 *  - Este DTO se procesa en UserDetailsServiceImpl.createUser() para:
 *      1. Validar y mapear roles
 *      2. Crear el UserEntity con roles y contraseñas encriptadas
 *      3. Generar el JWT inicial para el usuario creado
 *
 * ------------------------------------------------------------------
 */
public record AuthCreateUserDTO(
        @NotBlank String username,
        @NotBlank String password,
        @Valid AuthCreateRoleRequestDTO roleRequest
) {}
