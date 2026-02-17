package com.mglopez.crudstore.modules.producto.application;

import com.mglopez.crudstore.modules.producto.api.dtos.ProductoCreateDTO;
import com.mglopez.crudstore.modules.producto.api.dtos.ProductoResponseDTO;
import java.util.List;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Define el contrato de los casos de uso relacionados con la gestión
 * de productos dentro del sistema.
 *
 * Esta interfaz expone las operaciones que la aplicación permite
 * realizar sobre el dominio Producto, actuando como punto de entrada
 * a la lógica de negocio desde la capa API.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Application
 * Módulo : producto
 *
 * - Representa los casos de uso del sistema (Use Cases).
 * - Orquesta la interacción entre API, dominio e infraestructura.
 * - Define reglas de negocio a alto nivel mediante un contrato.
 *
 * La implementación concreta se encuentra en la clase
 * ProductoServiceImpl.
 *
 * ------------------------------------------------------------------
 * PRINCIPIOS DE DISEÑO
 * ------------------------------------------------------------------
 * - La API depende de esta interfaz, no de implementaciones concretas.
 * - Favorece desacoplamiento y testeo mediante inversión de dependencias.
 * - Utiliza DTOs para evitar exponer entidades del dominio directamente.
 *
 * ------------------------------------------------------------------
 * FLUJO GENERAL
 * ------------------------------------------------------------------
 * Controller → ProductoService → Repository → Base de datos
 *
 * El servicio:
 * - valida reglas de negocio
 * - coordina repositorios
 * - transforma entidades ↔ DTOs
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - No debe contener detalles de persistencia.
 * - No debe depender de controladores HTTP.
 * - Las implementaciones pueden cambiar sin afectar la API.
 * ------------------------------------------------------------------
 */
public interface ProductoService {

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param dto datos necesarios para la creación del producto
     * @return representación del producto creado
     */
    ProductoResponseDTO crearProducto(ProductoCreateDTO dto);

    /**
     * Obtiene un producto por su identificador único.
     *
     * @param id identificador del producto
     * @return producto encontrado
     */
    ProductoResponseDTO obtenerPorId(Long id);

    /**
     * Lista todos los productos activos del sistema.
     *
     * @return lista de productos activos
     */
    List<ProductoResponseDTO> listarProductosActivos();

    /**
     * Obtiene los productos pertenecientes a una categoría específica.
     *
     * @param categoriaId identificador de la categoría
     * @return lista de productos asociados
     */
    List<ProductoResponseDTO> listarProductosPorCategoria(Long categoriaId);

    /**
     * Actualiza la información de un producto existente.
     *
     * @param id identificador del producto a actualizar
     * @param dto nuevos datos del producto
     * @return producto actualizado
     */
    ProductoResponseDTO actualizarProducto(Long id, ProductoCreateDTO dto);

    /**
     * Elimina un producto del sistema.
     *
     * La implementación puede realizar eliminación lógica o física
     * según las reglas de negocio definidas.
     *
     * @param id identificador del producto
     */
    void eliminarProducto(Long id);

}
