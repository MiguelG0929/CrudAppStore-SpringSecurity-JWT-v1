package com.mglopez.crudstore.modules.auth.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * ------------------------------------------------------------------
 * ENTIDAD DE PERMISO (PermissionEntity)
 * ------------------------------------------------------------------
 * Representa un permiso específico dentro del sistema de autenticación.
 *
 * - Se asocia a roles (RoleEntity) para controlar el acceso a
 *   funcionalidades o endpoints de la aplicación.
 * - Define permisos de manera única mediante el campo 'name'.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Domain / Modelo de seguridad
 * Módulo : auth
 *
 * - Entidad de persistencia que se guarda en la tabla 'permissions'.
 * - No contiene lógica de negocio; solo representa el estado y la identidad del permiso.
 * - Se usa en conjunto con RoleEntity y UserEntity para la autorización.
 *
 * ------------------------------------------------------------------
 * CONSIDERACIONES DE DISEÑO
 * ------------------------------------------------------------------
 * - El campo 'name' es único y no actualizable para garantizar integridad.
 * - La relación con roles se define en RoleEntity (ManyToMany).
 * - Se recomienda usar constantes o enumeraciones de permisos para evitar errores de strings.
 *
 * ------------------------------------------------------------------
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    /**
     * Identificador único del permiso.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre único del permiso.
     *
     * - Ejemplo: "USER_READ", "PRODUCT_CREATE"
     * - No se puede actualizar para mantener consistencia histórica.
     */
    @Column(unique = true, nullable = false, updatable = false)
    private String name;
}
