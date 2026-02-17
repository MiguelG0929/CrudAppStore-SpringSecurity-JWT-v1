package com.mglopez.crudstore.modules.producto.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para operaciones de consulta de productos.
 * Incluye datos desnormalizados de categoría para evitar múltiples llamadas.
 */
public record ProductoResponseDTO(
        Long id,
        String name,
        String descripcion,
        BigDecimal precio,          // Usar BigDecimal para precisión monetaria
        Boolean activo,              // Soft delete: true = activo, false = eliminado
        Long categoriaId,
        String categoriaNombre,      // Desnormalizado para evitar join en cada consulta
        LocalDateTime fechaCreacion
) {}