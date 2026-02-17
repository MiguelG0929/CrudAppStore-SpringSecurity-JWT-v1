package com.mglopez.crudstore.shared.exception;

import java.time.LocalDateTime;

public record ErrorResponse
        (
                LocalDateTime timestamp,
                int status,
                String error,
                String message
        ) {}
