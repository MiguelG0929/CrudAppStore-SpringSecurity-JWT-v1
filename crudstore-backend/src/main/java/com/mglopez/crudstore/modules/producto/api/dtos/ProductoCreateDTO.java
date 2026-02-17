package com.mglopez.crudstore.modules.producto.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * DTO de entrada utilizado para crear un producto.
 *
 * Este record representa los datos que el cliente debe enviar
 * al backend cuando desea registrar un nuevo producto.
 */
public record ProductoCreateDTO(

        /**
         * Nombre del producto.
         * No puede estar vacío ni ser null.
         */
        @NotBlank
        String name,

        /**
         * Descripción del producto.
         * No puede estar vacía ni ser null.
         */
        @NotBlank
        String descripcion,

        /**
         * Precio del producto.
         * Debe ser un valor positivo y obligatorio.
         */
        @NotNull
        @Positive
        BigDecimal precio,

        /**
         * ID de la categoría a la que pertenece el producto.
         * Es obligatorio para asociar el producto con una categoría existente.
         */
        @NotNull
        Long categoriaId
) {}
