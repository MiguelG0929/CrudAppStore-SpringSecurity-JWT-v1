package com.mglopez.crudstore.modules.producto.infrastructure;

import com.mglopez.crudstore.modules.producto.domain.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Repositorio de acceso a datos para la entidad ProductoEntity.
 *
 * Proporciona las operaciones necesarias para interactuar con la
 * base de datos relacionadas con productos, delegando la mayor
 * parte de la implementación a Spring Data JPA.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Infrastructure
 * Módulo : producto
 *
 * - Actúa como adaptador de persistencia.
 * - Conecta el dominio con la base de datos.
 * - No contiene lógica de negocio.
 *
 * Este repositorio es utilizado por la capa Application para
 * ejecutar operaciones de lectura y escritura sobre productos.
 *
 * ------------------------------------------------------------------
 * IMPLEMENTACIÓN
 * ------------------------------------------------------------------
 * Extiende JpaRepository, lo que proporciona automáticamente:
 *
 * - Operaciones CRUD básicas.
 * - Paginación y ordenación.
 * - Integración con el contexto de persistencia de JPA.
 *
 * Spring genera automáticamente las consultas basándose en
 * los nombres de los métodos (Query Method Naming Strategy).
 *
 * ------------------------------------------------------------------
 * CONSULTAS DEFINIDAS
 * ------------------------------------------------------------------
 * - findByName(...)
 *      Busca un producto por su nombre.
 *
 * - findByActivoTrue()
 *      Obtiene únicamente productos activos (soft filtering).
 *
 * - findByCategoriaId(...)
 *      Recupera productos pertenecientes a una categoría.
 *
 * - findByCategoriaIdAndActivoTrue(...)
 *      Obtiene productos activos filtrados por categoría.
 *
 * ------------------------------------------------------------------
 * NOTAS DE DISEÑO
 * ------------------------------------------------------------------
 * - El repositorio no debe contener reglas de negocio ni validaciones.
 * - Las decisiones sobre qué productos mostrar o modificar deben
 *   realizarse en la capa Application (services).
 * - El uso de Optional evita valores nulos y fuerza manejo explícito
 *   de ausencia de resultados.
 * ------------------------------------------------------------------
 */
@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    /**
     * Busca un producto por su nombre.
     *
     * @param name nombre del producto
     * @return Optional con el producto si existe
     */
    Optional<ProductoEntity> findByName(String name);

    /**
     * Obtiene todos los productos marcados como activos.
     *
     * @return lista de productos activos
     */
    List<ProductoEntity> findByActivoTrue();

    /**
     * Obtiene todos los productos pertenecientes a una categoría específica.
     *
     * @param categoriaId identificador de la categoría
     * @return lista de productos asociados
     */
    List<ProductoEntity> findByCategoriaId(Long categoriaId);

    /**
     * Obtiene productos activos filtrados por categoría.
     *
     * @param categoriaId identificador de la categoría
     * @return lista de productos activos de la categoría indicada
     */
    List<ProductoEntity> findByCategoriaIdAndActivoTrue(Long categoriaId);

}
