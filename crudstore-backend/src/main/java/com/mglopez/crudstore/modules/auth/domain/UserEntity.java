package com.mglopez.crudstore.modules.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

/**
 * ------------------------------------------------------------------
 * ENTIDAD DE USUARIO (UserEntity)
 * ------------------------------------------------------------------
 * Representa un usuario del sistema con sus credenciales y roles.
 *
 * - Base para la autenticación y autorización con Spring Security.
 * - Contiene información sensible como contraseña y flags de cuenta.
 * - Se relaciona con RoleEntity para definir permisos y accesos.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Domain / Modelo de seguridad
 * Módulo : auth
 *
 * - Entidad de persistencia que se guarda en la tabla 'users'.
 * - No contiene lógica de negocio compleja; se usa como modelo de seguridad.
 * - Provee datos necesarios para UserDetailsService y JWT.
 *
 * ------------------------------------------------------------------
 * CONSIDERACIONES DE DISEÑO
 * ------------------------------------------------------------------
 * - username: único, usado para login.
 * - password: se debe almacenar encriptado (BCrypt o similar).
 * - isEnabled, accountNonExpired, accountNonLocked, credentialsNonExpired:
 *   flags de Spring Security para controlar estado de la cuenta.
 * - roles: relación ManyToMany con RoleEntity para asignar permisos.
 * - fetch = EAGER en roles para que Spring Security pueda cargar los roles al autenticar.
 * - No exponer password en respuestas de API.
 *
 * ------------------------------------------------------------------
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario único para autenticación.
     */
    @Column(unique = true)
    private String username;

    /**
     * Contraseña encriptada del usuario.
     *
     * - Nunca debe exponerse en la API.
     * - Se recomienda usar BCryptPasswordEncoder u otro algoritmo seguro.
     */
    private String password;

    /**
     * Indica si la cuenta está habilitada.
     */
    @Column(name = "is_enabled")
    private boolean isEnabled;

    /**
     * Indica si la cuenta no ha expirado.
     */
    @Column(name = "account_No_Expired")
    private boolean accountNonExpired;

    /**
     * Indica si la cuenta no está bloqueada.
     */
    @Column(name = "account_No_Locked")
    private boolean accountNonLocked;

    /**
     * Indica si las credenciales no han expirado.
     */
    @Column(name = "credentials_No_Expired")
    private boolean credentialsNonExpired;

    /**
     * Roles asignados al usuario.
     *
     * - Relación ManyToMany con RoleEntity.
     * - EAGER fetch para que los roles estén disponibles al autenticar.
     * - Define los permisos y accesos del usuario en la aplicación.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
