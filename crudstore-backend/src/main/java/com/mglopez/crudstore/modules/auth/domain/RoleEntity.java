package com.mglopez.crudstore.modules.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

/**
 * ------------------------------------------------------------------
 * ENTIDAD DE ROL (RoleEntity)
 * ------------------------------------------------------------------
 * Representa un rol dentro del sistema de autenticación.
 *
 * - Cada rol define un conjunto de permisos (PermissionEntity)
 *   que determinan qué acciones puede realizar un usuario.
 * - Se asigna a los usuarios mediante UserEntity.roles.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Domain / Modelo de seguridad
 * Módulo : auth
 *
 * - Entidad de persistencia que se guarda en la tabla 'roles'.
 * - Proporciona abstracción para la autorización basada en roles.
 * - Se utiliza junto con RoleEnum para evitar errores de string y
 *   asegurar consistencia.
 *
 * ------------------------------------------------------------------
 * CONSIDERACIONES DE DISEÑO
 * ------------------------------------------------------------------
 * - roleEnum: enum que define los posibles roles del sistema.
 * - permissionsList: relación ManyToMany con PermissionEntity.
 *   EAGER fetch para que los permisos estén disponibles al autenticar.
 * - Las relaciones ManyToMany utilizan tablas intermedias para
 *   mantener normalización de la base de datos.
 * - Se recomienda mantener roles y permisos inmutables después
 *   de su creación para garantizar seguridad.
 *
 * ------------------------------------------------------------------
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    /**
     * Identificador único del rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Enum que define el nombre del rol.
     *
     * - Ejemplo: ADMIN, USER
     * - Evita inconsistencias de strings.
     */
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    /**
     * Lista de permisos asociados al rol.
     *
     * - Relación ManyToMany con PermissionEntity.
     * - EAGER fetch para que los permisos estén disponibles al autenticar.
     * - Define qué acciones puede realizar un usuario con este rol.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permissions_id")
    )
    private Set<PermissionEntity> permissionsList = new HashSet<>();
}
