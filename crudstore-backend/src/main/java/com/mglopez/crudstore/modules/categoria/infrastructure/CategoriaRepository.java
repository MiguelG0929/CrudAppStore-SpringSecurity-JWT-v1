package com.mglopez.crudstore.modules.categoria.infrastructure;

import com.mglopez.crudstore.modules.categoria.domain.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Repositorio de acceso a datos para la entidad CategoriaEntity.
 *
 * - Proporciona operaciones CRUD básicas mediante JpaRepository.
 * - Permite consultas personalizadas por nombre y estado activo.
 * - Actúa como adaptador de persistencia entre la capa Application y la DB.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Infrastructure
 * Módulo : categoria
 *
 * - No contiene lógica de negocio.
 * - La capa Application (services) delega aquí las operaciones de persistencia.
 * - Las consultas devuelven entidades del dominio, que luego se transforman en DTOs.
 *
 * ------------------------------------------------------------------
 * CONSULTAS DEFINIDAS
 * ------------------------------------------------------------------
 * - existsByNombre(String nombre)
 *      Verifica la existencia de una categoría con el nombre dado.
 *
 * - findByNombre(String nombre)
 *      Recupera una categoría por su nombre, útil para validaciones o búsquedas.
 *
 * - findByActivaTrue()
 *      Lista únicamente las categorías activas en el sistema.
 *
 * ------------------------------------------------------------------
 * NOTAS DE DISEÑO
 * ------------------------------------------------------------------
 * - Se recomienda que toda lógica de negocio (validaciones, reglas)
 *   se realice en la capa Application.
 * - Optional se utiliza para forzar manejo explícito de ausencia de resultados.
 * - Las consultas personalizadas aprovechan la convención de nombres de Spring Data JPA.
 * ------------------------------------------------------------------
 */
@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    /**
     * Comprueba si existe una categoría con el nombre indicado.
     *
     * @param nombre nombre de la categoría
     * @return true si ya existe, false si no
     */
    boolean existsByNombre(String nombre);

    /**
     * Busca una categoría por su nombre.
     *
     * @param nombre nombre de la categoría
     * @return Optional con la categoría si existe
     */
    Optional<CategoriaEntity> findByNombre(String nombre);

    /**
     * Obtiene todas las categorías activas.
     *
     * @return lista de categorías activas
     */
    List<CategoriaEntity> findByActivaTrue();

}
