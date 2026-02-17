package com.mglopez.crudstore.modules.auth.infrastructure.repositories;

import com.mglopez.crudstore.modules.auth.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ------------------------------------------------------------------
 * REPOSITORIO DE USUARIOS (UserRepository)
 * ------------------------------------------------------------------
 * Encargado de la persistencia y consultas de UserEntity.
 *
 * - Permite acceder, guardar, actualizar y eliminar usuarios
 *   en la base de datos.
 * - Se integra con Spring Data JPA para simplificar operaciones CRUD.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Infrastructure / Repository
 * Módulo : auth
 *
 * - Abstracción de la capa de persistencia para UserEntity.
 * - Usado principalmente por UserDetailsServiceImpl y servicios de Auth
 *   para autenticación y gestión de usuarios.
 *
 * ------------------------------------------------------------------
 * MÉTODOS PRINCIPALES
 * ------------------------------------------------------------------
 * - findUserEntityByUsername(String username)
 *      - Consulta un usuario por su nombre de usuario.
 *      - Retorna Optional<UserEntity> para manejar ausencia de usuario
 *        de forma segura.
 *
 * - CRUD estándar heredado de JpaRepository:
 *      - save, findById, findAll, deleteById, etc.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - Es la fuente de datos principal para Spring Security al autenticar
 *   usuarios.
 * - La búsqueda por username es crítica para login y validación JWT.
 * ------------------------------------------------------------------
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return Optional<UserEntity> con el usuario si existe
     */
    Optional<UserEntity> findUserEntityByUsername(String username);
}
