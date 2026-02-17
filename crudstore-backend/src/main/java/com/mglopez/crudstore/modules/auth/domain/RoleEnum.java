package com.mglopez.crudstore.modules.auth.domain;

/**
 * ------------------------------------------------------------------
 * ENUM DE ROLES (RoleEnum)
 * ------------------------------------------------------------------
 * Define los roles posibles que pueden asignarse a los usuarios
 * dentro del sistema.
 *
 * - Se utiliza en RoleEntity.roleEnum para garantizar consistencia
 *   y evitar errores de strings.
 * - Permite la autorización basada en roles (Role-Based Access Control).
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Domain / Modelo de seguridad
 * Módulo : auth
 *
 * - Enum simple que sirve como fuente de verdad para roles válidos.
 * - Facilita validaciones, comparaciones y asignaciones en servicios
 *   y controladores.
 *
 * ------------------------------------------------------------------
 * ROLES DEFINIDOS
 * ------------------------------------------------------------------
 * - ADMIN     : rol con todos los permisos administrativos.
 * - USER      : rol de usuario estándar con permisos limitados.
 * - INVITED   : rol temporal o con permisos muy restringidos.
 * - DEVELOPER : rol para usuarios de desarrollo o pruebas internas.
 *
 * ------------------------------------------------------------------
 * BUENAS PRÁCTICAS
 * ------------------------------------------------------------------
 * - Usar enums evita inconsistencias de strings en base de datos
 *   y código.
 * - Se recomienda documentar cada rol y su alcance de permisos.
 * ------------------------------------------------------------------
 */
public enum RoleEnum {

    ADMIN,
    USER,
    INVITED,
    DEVELOPER
}
