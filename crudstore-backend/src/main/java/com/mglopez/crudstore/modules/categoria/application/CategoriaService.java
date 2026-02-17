package com.mglopez.crudstore.modules.categoria.application;

import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaCreateDTO;
import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaResponseDTO;
import java.util.List;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Interfaz que define los casos de uso relacionados con la gestión
 * de categorías dentro del sistema.
 *
 * - Sirve como contrato entre la capa API (Controllers) y la lógica
 *   de negocio en la capa Application.
 * - No expone entidades del dominio directamente; usa DTOs para
 *   entrada y salida.
 * - Cada método representa un caso de uso concreto sobre categorías.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Application
 * Módulo : categoria
 *
 * - Coordina la interacción entre repositorios y DTOs.
 * - Permite que la API sea desacoplada del dominio.
 * - Facilita pruebas unitarias y mantenimiento.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - La implementación concreta se encuentra en CategoriaServiceImpl.
 * - Los métodos que eliminan o desactivan categorías aplican
 *   eliminación lógica mediante el campo "activa".
 * - Todos los métodos que retornan información utilizan DTOs para
 *   proteger la integridad del dominio y desacoplar la capa de persistencia.
 * ------------------------------------------------------------------
 */
public interface CategoriaService {

    /**
     * Crea una nueva categoría.
     *
     * @param categoriaCreateDTO DTO con los datos de la categoría
     * @return DTO con la categoría creada
     */
    CategoriaResponseDTO crearCategoria(CategoriaCreateDTO categoriaCreateDTO);

    /**
     * Lista todas las categorías activas.
     *
     * @return lista de categorías activas en formato DTO
     */
    List<CategoriaResponseDTO> listarCategoriasActivas();

    /**
     * Obtiene una categoría por su identificador único.
     *
     * @param id ID de la categoría
     * @return DTO con los datos de la categoría
     */
    CategoriaResponseDTO obtenerPorId(Long id);

    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param id ID de la categoría a actualizar
     * @param categoriaCreateDTO DTO con los nuevos datos
     * @return DTO con la categoría actualizada
     */
    CategoriaResponseDTO actualizarCategoria(Long id, CategoriaCreateDTO categoriaCreateDTO);

    /**
     * Elimina (desactiva) una categoría.
     *
     * - No se elimina físicamente; se cambia el campo "activa" a false.
     *
     * @param id ID de la categoría a eliminar
     */
    void eliminarCategoria(Long id);

}
