package com.mglopez.crudstore.modules.categoria.application.impl;

import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaCreateDTO;
import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaResponseDTO;
import com.mglopez.crudstore.modules.categoria.application.CategoriaService;
import com.mglopez.crudstore.modules.categoria.domain.CategoriaEntity;
import com.mglopez.crudstore.modules.categoria.infrastructure.CategoriaRepository;
import com.mglopez.crudstore.shared.exception.BadRequestException;
import com.mglopez.crudstore.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ------------------------------------------------------------------
 * IMPLEMENTACIÓN DE CATEGORÍA
 * ------------------------------------------------------------------
 * Clase que implementa la interfaz CategoriaService.
 *
 * - Contiene la lógica de negocio relacionada con categorías.
 * - Coordina el acceso a datos a través del repositorio.
 * - Realiza validaciones y transformación de entidades a DTOs.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Application / Service
 * Módulo : categoria
 *
 * - Se encarga de los casos de uso: crear, listar, obtener,
 *   actualizar y eliminar categorías.
 * - Garantiza integridad de datos y reglas de negocio.
 * - No expone entidades directamente a la API.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - La eliminación es lógica mediante el campo "activa".
 * - Se valida la unicidad de nombre al crear y actualizar.
 * - Se utiliza transacción (@Transactional) para asegurar consistencia.
 * - MapToResponseDTO separa la entidad de la capa de presentación.
 * ------------------------------------------------------------------
 */
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    /**
     * Inyección de dependencias por constructor.
     *
     * @param categoriaRepository repositorio de acceso a datos de Categoría
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Crea una nueva categoría.
     *
     * - Valida que no exista otra categoría con el mismo nombre.
     * - Activa la categoría por defecto.
     *
     * @param dto DTO con los datos de la categoría a crear
     * @return DTO con los datos de la categoría creada
     */
    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaCreateDTO dto) {
        if(categoriaRepository.existsByNombre(dto.nombre())) {
            throw new BadRequestException("La categoría ya existe: " + dto.nombre());
        }

        CategoriaEntity categoria = CategoriaEntity.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .activa(true)
                .build();

        categoriaRepository.save(categoria);

        return mapToResponseDTO(categoria);
    }

    /**
     * Lista todas las categorías activas.
     *
     * @return lista de categorías activas en formato DTO
     */
    @Override
    public List<CategoriaResponseDTO> listarCategoriasActivas() {
        return categoriaRepository.findByActivaTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id identificador de la categoría
     * @return DTO de la categoría encontrada
     * @throws IllegalArgumentException si no existe la categoría
     */
    @Override
    public CategoriaResponseDTO obtenerPorId(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID " + id));
        return mapToResponseDTO(categoria);
    }

    /**
     * Actualiza una categoría existente.
     *
     * - Valida que si se cambia el nombre, no exista otra categoría con el mismo nombre.
     *
     * @param id  ID de la categoría a actualizar
     * @param dto DTO con los nuevos datos
     * @return DTO de la categoría actualizada
     */
    @Override
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaCreateDTO dto) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID " + id ));

        if (!categoria.getNombre().equals(dto.nombre()) && categoriaRepository.existsByNombre(dto.nombre())) {
            throw new BadRequestException("Otra categoría ya tiene el nombre: " + dto.nombre());

        }

        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());

        categoria = categoriaRepository.save(categoria);

        return mapToResponseDTO(categoria);
    }

    /**
     * Elimina (desactiva) una categoría.
     *
     * - La eliminación es lógica: se cambia el campo "activa" a false.
     *
     * @param id ID de la categoría a eliminar
     */
    @Override
    public void eliminarCategoria(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID " + id ));

        categoria.setActiva(false);
        categoriaRepository.save(categoria);
    }

    /**
     * Método auxiliar para mapear CategoriaEntity a CategoriaResponseDTO.
     *
     * - Mantiene desacoplamiento entre dominio y API.
     *
     * @param categoria entidad de categoría
     * @return DTO de respuesta
     */
    private CategoriaResponseDTO mapToResponseDTO(CategoriaEntity categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getActiva(),
                categoria.getFechaCreacion()
        );
    }
}
