package com.mglopez.crudstore.modules.auth.api.dtos;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * ------------------------------------------------------------------
 * DTO DE ROLES PARA CREACIÓN DE USUARIOS (AuthCreateRoleRequestDTO)
 * ------------------------------------------------------------------
 * Este record encapsula la lista de roles que se asignarán a un usuario
 * al momento de su creación.
 *
 * Campos:
 *  - roleListName: lista de nombres de roles que el usuario tendrá
 *                  (ej. "ADMIN", "USER", "DEVELOPER")
 *
 * Validaciones:
 *  - @Size(max = 3) limita a un máximo de 3 roles por usuario
 *    para evitar asignaciones excesivas o incorrectas.
 *  - @Validated habilita la validación automática de Spring sobre el record.
 *
 * Uso:
 *  - Incluido dentro de AuthCreateUserDTO
 *  - Procesado en UserDetailsServiceImpl.createUser() para:
 *      1. Convertir los nombres de roles a RoleEnum
 *      2. Validar que los roles existan en la base de datos
 *      3. Asignar los roles al UserEntity
 *
 * ------------------------------------------------------------------
 */
@Validated
public record AuthCreateRoleRequestDTO(
        @Size(max = 3, message = "The user cannot have no more than 3 roles") List<String> roleListName
) {}
