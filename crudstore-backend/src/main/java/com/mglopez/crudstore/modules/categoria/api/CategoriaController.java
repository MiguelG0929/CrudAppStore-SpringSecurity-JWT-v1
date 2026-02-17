package com.mglopez.crudstore.modules.categoria.api;

import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaCreateDTO;
import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaResponseDTO;
import com.mglopez.crudstore.modules.categoria.application.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ------------------------------------------------------------------
 * CONTROLADOR DE CATEGORÍAS
 * ------------------------------------------------------------------
 * Exposición de los endpoints REST para la gestión de categorías.
 *
 * - Coordina la interacción entre el cliente y la capa Application
 *   mediante el servicio CategoriaService.
 * - Valida la entrada de datos usando @Valid y DTOs.
 * - Retorna respuestas HTTP con códigos apropiados (200, 201, 204).
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : API / Controller
 * Módulo : categoria
 *
 * - Controlador REST encargado de recibir peticiones HTTP y
 *   delegarlas al servicio.
 * - No contiene lógica de negocio; toda la lógica está en
 *   CategoriaServiceImpl.
 * - Utiliza DTOs de entrada y salida para desacoplar la API del dominio.
 *
 * ------------------------------------------------------------------
 * ENDPOINTS
 * ------------------------------------------------------------------
 * POST /api/categorias/create
 *      - Crear nueva categoría.
 *      - Recibe CategoriaCreateDTO.
 *      - Responde 201 CREATED con CategoriaResponseDTO.
 *
 * GET /api/categorias
 *      - Lista todas las categorías activas.
 *      - Responde 200 OK con lista de CategoriaResponseDTO.
 *
 * GET /api/categorias/{id}
 *      - Obtiene una categoría por ID.
 *      - Responde 200 OK o lanza excepción si no existe.
 *
 * PUT /api/categorias/{id}
 *      - Actualiza una categoría existente.
 *      - Recibe CategoriaCreateDTO.
 *      - Responde 200 OK con CategoriaResponseDTO actualizado.
 *
 * DELETE /api/categorias/{id}
 *      - Elimina (desactiva) una categoría.
 *      - Respuesta 204 NO CONTENT.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - Todos los endpoints utilizan DTOs para garantizar seguridad
 *   y desacoplamiento de la entidad de dominio.
 * - Las validaciones se realizan automáticamente gracias a @Valid.
 * - Los códigos de estado HTTP siguen las buenas prácticas REST.
 * ------------------------------------------------------------------
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Crea una nueva categoría.
     *
     * @param dto DTO de entrada con los datos de la categoría
     * @return DTO de la categoría creada con código 201
     */
    @PostMapping("/create")
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(
            @Valid @RequestBody CategoriaCreateDTO dto) {

        CategoriaResponseDTO categoriaCreada = categoriaService.crearCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
    }

    /**
     * Lista todas las categorías activas.
     *
     * @return lista de CategoriaResponseDTO con código 200 OK
     */
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategoriasActivas() {
        return ResponseEntity.ok(categoriaService.listarCategoriasActivas());
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id ID de la categoría
     * @return DTO de la categoría con código 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id  ID de la categoría a actualizar
     * @param dto DTO con los datos nuevos
     * @return DTO actualizado con código 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaCreateDTO dto) {

        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, dto));
    }

    /**
     * Elimina (desactiva) una categoría.
     *
     * - La eliminación es lógica; la categoría se marca como inactiva.
     *
     * @param id ID de la categoría
     * @return respuesta 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
