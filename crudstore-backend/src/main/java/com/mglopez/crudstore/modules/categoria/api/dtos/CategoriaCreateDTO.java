package com.mglopez.crudstore.modules.categoria.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ------------------------------------------------------------------
 * DTO DE ENTRADA: CategoriaCreateDTO
 * ------------------------------------------------------------------
 * Objeto de transferencia de datos usado para la creación y
 * actualización de categorías en la API.
 *
 * - Se utiliza en los endpoints de CategoriaController.
 * - Garantiza que los datos enviados por el cliente cumplan
 *   con las reglas de validación antes de llegar al service.
 *
 * ------------------------------------------------------------------
 * VALIDACIONES
 * ------------------------------------------------------------------
 * - nombre: obligatorio (@NotBlank) y máximo 50 caracteres.
 * - descripcion: opcional, máximo 150 caracteres.
 *
 * ------------------------------------------------------------------
 * ROL ARQUITECTÓNICO
 * ------------------------------------------------------------------
 * Capa   : API / DTO
 * Módulo : categoria
 *
 * - Protege la entidad de dominio evitando exponerla directamente.
 * - Permite desacoplar la capa de presentación (API) de la lógica
 *   de negocio (Application).
 * ------------------------------------------------------------------
 */
public record CategoriaCreateDTO (

        @NotBlank
        @Size(max = 50)
        String nombre,

        @Size(max = 150)
        String descripcion

) {}
