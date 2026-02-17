package com.mglopez.crudstore.modules.auth.infrastructure.repositories;

import com.mglopez.crudstore.modules.auth.domain.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ------------------------------------------------------------------
 * REPOSITORIO DE PERMISOS (PermissionRepository)
 * ------------------------------------------------------------------
 * Encargado de la persistencia y consultas de PermissionEntity.
 *
 * - Permite acceder, guardar, actualizar y eliminar permisos
 *   en la base de datos.
 * - Facilita la asignación de permisos a roles (RoleEntity).
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Infrastructure / Repository
 * Módulo : auth
 *
 * - Abstracción de la capa de persistencia para PermissionEntity.
 * - Usado por servicios de autorización y administración de roles
 *   para cargar y verificar permisos.
 *
 * ------------------------------------------------------------------
 * MÉTODOS PRINCIPALES
 * ------------------------------------------------------------------
 * - CRUD estándar heredado de JpaRepository:
 *      - save, findById, findAll, deleteById, etc.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - Los permisos se cargan junto con los roles para control de acceso.
 * - Se recomienda mantener los permisos inmutables después de su creación
 *   para garantizar seguridad y consistencia.
 * ------------------------------------------------------------------
 */
@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    // No se requieren métodos personalizados adicionales por ahora.
}
