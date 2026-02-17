package com.mglopez.crudstore.modules.auth.infrastructure.repositories;

import com.mglopez.crudstore.modules.auth.domain.RoleEntity;
import com.mglopez.crudstore.modules.auth.domain.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ------------------------------------------------------------------
 * REPOSITORIO DE ROLES (RoleRepository)
 * ------------------------------------------------------------------
 * Encargado de la persistencia y consultas de RoleEntity.
 *
 * - Permite acceder, guardar, actualizar y eliminar roles
 *   en la base de datos.
 * - Facilita la asignación de roles a usuarios y la verificación
 *   de permisos.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Infrastructure / Repository
 * Módulo : auth
 *
 * - Abstracción de la capa de persistencia para RoleEntity.
 * - Usado por servicios de autenticación y autorización
 *   para cargar roles y permisos.
 *
 * ------------------------------------------------------------------
 * MÉTODOS PRINCIPALES
 * ------------------------------------------------------------------
 * - findRoleEntitiesByRoleEnumIn(List<RoleEnum> roles)
 *      - Consulta todos los roles cuyo RoleEnum esté en la lista proporcionada.
 *      - Útil para asignar múltiples roles a un usuario de manera segura.
 *
 * - CRUD estándar heredado de JpaRepository:
 *      - save, findById, findAll, deleteById, etc.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - Los roles se cargan con sus permisos asociados para autorización.
 * - Mantener consistencia con RoleEnum para evitar errores de strings.
 * ------------------------------------------------------------------
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Busca todos los roles cuyo RoleEnum esté en la lista proporcionada.
     *
     * @param roles Lista de enums de roles
     * @return Lista de RoleEntity que coinciden
     */
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<RoleEnum> roles);
}
