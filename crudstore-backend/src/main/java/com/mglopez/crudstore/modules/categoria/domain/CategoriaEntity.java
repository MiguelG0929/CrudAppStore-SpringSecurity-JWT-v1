package com.mglopez.crudstore.modules.categoria.domain;

import com.mglopez.crudstore.modules.producto.domain.ProductoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Entidad que representa una categoría de productos dentro del sistema.
 *
 * - Define los atributos esenciales de una categoría.
 * - Permite organizar productos en grupos lógicos.
 * - Mantiene información de estado (activa/inactiva) y fecha de creación.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Domain
 * Módulo : categoria
 *
 * - Modelo del dominio, persistido mediante JPA.
 * - No contiene lógica de negocio.
 * - Sirve como base para operaciones en Application y API.
 *
 * ------------------------------------------------------------------
 * MAPEO A BASE DE DATOS
 * ------------------------------------------------------------------
 * Tabla: categorias
 *
 * - `id`: identificador autogenerado.
 * - `nombre`: único, obligatorio, longitud máxima 50.
 * - `descripcion`: opcional, longitud máxima 150.
 * - `activa`: indica si la categoría se encuentra habilitada.
 * - `fechaCreacion`: generada automáticamente, inmutable.
 *
 * ------------------------------------------------------------------
 * RELACIONES
 * ------------------------------------------------------------------
 * One-to-Many bidireccional con ProductoEntity:
 * - Una categoría puede tener múltiples productos.
 * - FetchType.LAZY para optimizar consultas.
 * - Excluido de equals, hashCode y toString para evitar ciclos infinitos.
 *
 * ------------------------------------------------------------------
 * NOTAS DE DISEÑO
 * ------------------------------------------------------------------
 * - Se inicializa `productos` con HashSet para evitar NullPointerException.
 * - El estado `activa` permite implementar soft delete o control de visibilidad.
 * - Uso de anotaciones Bean Validation para garantizar integridad de datos.
 * ------------------------------------------------------------------
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la categoría.
     * Obligatorio y único para evitar duplicados.
     */
    @NotBlank
    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    /**
     * Descripción opcional de la categoría.
     */
    @Column(length = 150)
    private String descripcion;

    /**
     * Indica si la categoría se encuentra activa en el sistema.
     * Permite soft delete o control de visibilidad.
     */
    @NotNull
    private Boolean activa;

    /**
     * Fecha de creación automática.
     * Inmutable una vez persistida la entidad.
     */
    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Relación bidireccional con productos.
     *
     * - Una categoría puede contener múltiples productos.
     * - FetchType.LAZY para evitar cargas innecesarias.
     * - Excluido de equals, hashCode y toString para evitar ciclos
     *   infinitos y problemas de rendimiento.
     */
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ProductoEntity> productos = new HashSet<>();

}
