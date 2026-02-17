package com.mglopez.crudstore.modules.producto.api;

import com.mglopez.crudstore.modules.producto.api.dtos.ProductoCreateDTO;
import com.mglopez.crudstore.modules.producto.api.dtos.ProductoResponseDTO;
import com.mglopez.crudstore.modules.producto.application.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Controlador REST que expone los endpoints de la API para la
 * gestión de productos.
 *
 * - Recibe las solicitudes HTTP del cliente.
 * - Valida los datos de entrada mediante DTOs y anotaciones de
 *   Bean Validation.
 * - Delegación de la lógica de negocio a ProductoService.
 * - Mapeo de resultados hacia DTOs de respuesta.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : API / Controller
 * Módulo : producto
 *
 * - Punto de entrada de la aplicación para clientes REST.
 * - No debe contener lógica de negocio ni acceso a base de datos.
 * - Maneja códigos HTTP y estructura de respuesta.
 *
 * ------------------------------------------------------------------
 * NOTAS IMPORTANTES
 * ------------------------------------------------------------------
 * - Todos los endpoints trabajan con DTOs para desacoplar el dominio.
 * - Se utilizan ResponseEntity para controlar status HTTP:
 *      - 201 CREATED para creación
 *      - 200 OK para consultas y actualizaciones
 *      - 204 NO CONTENT para eliminación
 * - @Valid asegura que los datos de entrada cumplen con las reglas
 *   definidas en ProductoCreateDTO antes de llamar al service.
 * ------------------------------------------------------------------
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Inyección de dependencias por constructor.
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Crea un nuevo producto.
     *
     * @param dto DTO con los datos del producto a crear
     * @return ProductoResponseDTO con los datos del producto creado
     *         y código HTTP 201 CREATED
     */
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoCreateDTO dto) {

        ProductoResponseDTO productoCreado = productoService.crearProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    /**
     * Lista todos los productos activos.
     *
     * @return Lista de productos activos con código HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductosActivos() {
        return ResponseEntity.ok(productoService.listarProductosActivos());
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id identificador del producto
     * @return ProductoResponseDTO encontrado con código HTTP 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    /**
     * Lista productos activos filtrados por categoría.
     *
     * @param categoriaId ID de la categoría
     * @return Lista de productos activos de la categoría con código 200 OK
     */
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDTO>> listarPorCategoria(
            @PathVariable Long categoriaId) {

        return ResponseEntity.ok(
                productoService.listarProductosPorCategoria(categoriaId)
        );
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id  identificador del producto a actualizar
     * @param dto DTO con los nuevos datos
     * @return ProductoResponseDTO actualizado con código 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoCreateDTO dto) {

        return ResponseEntity.ok(
                productoService.actualizarProducto(id, dto)
        );
    }

    /**
     * Elimina un producto de manera lógica.
     *
     * @param id identificador del producto a eliminar
     * @return código HTTP 204 NO CONTENT indicando éxito
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
