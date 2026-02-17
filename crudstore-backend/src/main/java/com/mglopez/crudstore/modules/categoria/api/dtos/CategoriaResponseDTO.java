package com.mglopez.crudstore.modules.categoria.api.dtos;

import java.time.LocalDateTime;

/**
 * ------------------------------------------------------------------
 * DTO DE SALIDA: CategoriaResponseDTO
 * ------------------------------------------------------------------
 * Objeto de transferencia de datos usado para enviar información
 * de categorías desde la API hacia el cliente.
 *
 * - Se devuelve en respuestas de los endpoints de CategoriaController.
 * - Contiene únicamente la información necesaria para el consumidor
 *   de la API, protegiendo la entidad de dominio.
 *
 * ------------------------------------------------------------------
 * CAMPOS INCLUIDOS
 * ------------------------------------------------------------------
 * - id: identificador único de la categoría.
 * - nombre: nombre de la categoría.
 * - descripcion: descripción de la categoría.
 * - activa: indica si la categoría está activa o desactivada.
 * - fechaCreacion: timestamp de creación de la categoría.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : API / DTO
 * Módulo : categoria
 *
 * - Desacopla la capa de presentación (API) del dominio.
 * - Facilita la serialización a JSON para clientes REST.
 * ------------------------------------------------------------------
 */
public record CategoriaResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        Boolean activa,
        LocalDateTime fechaCreacion
) {}
