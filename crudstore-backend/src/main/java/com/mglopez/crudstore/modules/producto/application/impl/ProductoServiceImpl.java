package com.mglopez.crudstore.modules.producto.application.impl;

import com.mglopez.crudstore.modules.categoria.domain.CategoriaEntity;
import com.mglopez.crudstore.modules.categoria.infrastructure.CategoriaRepository;
import com.mglopez.crudstore.modules.producto.api.dtos.ProductoCreateDTO;
import com.mglopez.crudstore.modules.producto.api.dtos.ProductoResponseDTO;
import com.mglopez.crudstore.modules.producto.application.ProductoService;
import com.mglopez.crudstore.modules.producto.domain.ProductoEntity;
import com.mglopez.crudstore.modules.producto.infrastructure.ProductoRepository;
import com.mglopez.crudstore.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Implementación de los casos de uso definidos en ProductoService.
 *
 * Contiene la lógica de aplicación encargada de coordinar la
 * interacción entre el dominio Producto, los repositorios y la
 * transformación de datos hacia DTOs utilizados por la capa API.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Application
 * Módulo : producto
 *
 * - Orquesta operaciones del sistema relacionadas con productos.
 * - Aplica reglas de negocio básicas.
 * - Gestiona transacciones.
 * - Convierte entidades del dominio en DTOs de respuesta.
 *
 * No debe contener:
 * - lógica HTTP
 * - detalles de persistencia complejos
 * - configuraciones de infraestructura
 *
 * ------------------------------------------------------------------
 * TRANSACCIONALIDAD
 * ------------------------------------------------------------------
 * La anotación @Transactional asegura que cada operación se ejecute
 * dentro de una transacción de base de datos, garantizando
 * consistencia ante errores.
 *
 * ------------------------------------------------------------------
 * DEPENDENCIAS
 * ------------------------------------------------------------------
 * - ProductoRepository → acceso a datos de productos.
 * - CategoriaRepository → validación y asociación de categorías.
 *
 * Se utiliza inyección por constructor para favorecer:
 * - inmutabilidad
 * - testabilidad
 * - buenas prácticas recomendadas por Spring.
 * ------------------------------------------------------------------
 */
@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    /**
     * Inyección de dependencias mediante constructor.
     * Método recomendado frente a @Autowired por claridad y testabilidad.
     */
    public ProductoServiceImpl(ProductoRepository productoRepository,
                               CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Crea un nuevo producto validando previamente la existencia
     * de la categoría asociada.
     *
     * Flujo:
     * DTO → Entidad → Persistencia → DTO respuesta
     */
    @Override
    public ProductoResponseDTO crearProducto(ProductoCreateDTO dto) {

        CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                                "Categoría no encontrada con ID: " + dto.categoriaId()
                        ));

        ProductoEntity producto = ProductoEntity.builder()
                .name(dto.name())
                .descripcion(dto.descripcion())
                .precio(dto.precio())
                .activo(true) // creación siempre inicia como activo
                .categoria(categoria)
                .build();

        producto = productoRepository.save(producto);

        return mapToResponseDTO(producto);
    }

    /**
     * Obtiene un producto por su identificador único.
     * Lanza excepción si el producto no existe.
     */
    @Override
    public ProductoResponseDTO obtenerPorId(Long id) {

        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado con ID: " + id
                        ));

        return mapToResponseDTO(producto);
    }

    /**
     * Recupera únicamente los productos activos del sistema.
     * Implementa filtrado lógico basado en el campo "activo".
     */
    @Override
    public List<ProductoResponseDTO> listarProductosActivos() {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lista productos activos pertenecientes a una categoría específica.
     */
    @Override
    public List<ProductoResponseDTO> listarProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaIdAndActivoTrue(categoriaId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * Se valida:
     * - existencia del producto
     * - existencia de la categoría asociada
     */
    @Override
    public ProductoResponseDTO actualizarProducto(Long id, ProductoCreateDTO dto) {

        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado con ID: " + id
                        ));

        CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Categoría no encontrada con ID: " + dto.categoriaId()
                        ));

        producto.setName(dto.name());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setCategoria(categoria);

        producto = productoRepository.save(producto);

        return mapToResponseDTO(producto);
    }

    /**
     * Realiza una eliminación lógica del producto.
     *
     * En lugar de borrar el registro físicamente, se marca como
     * inactivo para preservar historial y evitar pérdida de datos.
     */
    @Override
    public void eliminarProducto(Long id) {

        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado con ID: " + id
                        ));

        producto.setActivo(false);
        productoRepository.save(producto);
    }

    /**
     * Convierte una entidad ProductoEntity en un DTO de respuesta.
     *
     * Centralizar el mapeo evita duplicación y mantiene separadas
     * las capas Domain y API.
     */
    private ProductoResponseDTO mapToResponseDTO(ProductoEntity producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getName(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getActivo(),
                producto.getCategoria().getId(),
                producto.getCategoria().getNombre(),
                producto.getFechaCreacion()
        );
    }
}
