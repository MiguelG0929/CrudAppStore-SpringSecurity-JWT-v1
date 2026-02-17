package com.mglopez.crudstore.modules.producto.domain;

import com.mglopez.crudstore.modules.categoria.domain.CategoriaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ------------------------------------------------------------------
 * RESPONSABILIDAD
 * ------------------------------------------------------------------
 * Representa la entidad Producto dentro del dominio del módulo
 * "producto".
 *
 * Modela el concepto principal de negocio relacionado con los
 * productos almacenados en el sistema, incluyendo información
 * básica, precio, estado activo y su relación con una categoría.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : Domain
 * Módulo : producto
 *
 * - Contiene la representación del modelo de dominio persistente.
 * - Define únicamente el estado del negocio.
 * - No debe contener lógica de aplicación ni dependencias de
 *   servicios externos.
 *
 * Aunque utiliza anotaciones JPA, su propósito es representar
 * el modelo del dominio y no la lógica de infraestructura.
 *
 * ------------------------------------------------------------------
 * MAPEO A BASE DE DATOS
 * ------------------------------------------------------------------
 * Tabla: productos
 *
 * Características principales:
 * - Identificador autogenerado.
 * - Uso de BigDecimal para precisión en valores monetarios.
 * - Fecha de creación generada automáticamente por Hibernate.
 *
 * ------------------------------------------------------------------
 * RELACIONES
 * ------------------------------------------------------------------
 * Many-to-One → CategoriaEntity
 *
 * Cada producto pertenece obligatoriamente a una categoría.
 * Se utiliza carga LAZY para evitar consultas innecesarias y
 * mejorar el rendimiento.
 *
 * ------------------------------------------------------------------
 * VALIDACIONES
 * ------------------------------------------------------------------
 * Se emplea Bean Validation para garantizar integridad de datos:
 *
 * - name / descripcion → no pueden estar vacíos.
 * - precio → debe ser un valor positivo.
 * - categoria → relación obligatoria.
 *
 * ------------------------------------------------------------------
 * DECISIONES DE DISEÑO
 * ------------------------------------------------------------------
 * - Se excluye la categoría de equals/hashCode y toString para
 *   evitar ciclos infinitos y problemas de rendimiento.
 * - fechaCreacion es inmutable tras la persistencia.
 * - activo permite desactivar productos sin eliminarlos
 *   físicamente (soft state).
 * ------------------------------------------------------------------
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank
    @Column(length = 255)
    private String descripcion;

    @NotNull
    @Positive
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    /**
     * Fecha de creación generada automáticamente al persistir
     * la entidad. No puede modificarse posteriormente.
     */
    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Indica si el producto se encuentra activo dentro del sistema.
     * Permite habilitar o deshabilitar sin eliminar el registro.
     */
    @NotNull
    private Boolean activo;

    /**
     * Relación con la categoría a la que pertenece el producto.
     *
     * FetchType.LAZY evita cargar la categoría automáticamente
     * hasta que sea necesaria.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoriaEntity categoria;

}
