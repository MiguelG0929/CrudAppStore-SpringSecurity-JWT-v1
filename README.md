# CrudAppStore-SpringSecurity-JWT-v1
App Centrada en el manejo de seguridad con JWT

!NOTA: El objetivo del proyecto es el manejo de SpringSecurity + JWT para mejor experiencia correr solo el backend + postman.

🛒 CRUDStore Backend
Backend modular y escalable para un sistema de gestión de productos y categorías con autenticación JWT y roles de usuario.

📋 Descripción

CRUDStore Backend es un proyecto de ejemplo profesional que implementa:

Gestión de productos y categorías (CRUD completo con eliminación lógica).

Sistema de autenticación y autorización con Spring Security y JWT.

Roles y permisos flexibles para controlar acceso.

Excepciones globales para respuestas uniformes en la API.

Arquitectura modular, limpia y fácil de mantener.

Este proyecto es ideal para aprender buenas prácticas de Spring Boot, DDD ligero, manejo de JWT, y diseño de APIs robustas.

🗂 Estructura del proyecto
crudstore-backend/
├── src/main/java/com/mglopez/crudstore/
│   ├── config/                  # Configuraciones generales (CORS, DataInitializer)
│   ├── modules/
│   │   ├── auth/                # Módulo de autenticación y autorización
│   │   │   ├── api/             # Controllers y DTOs
│   │   │   ├── domain/          # Entidades User, Role, Permission
│   │   │   ├── infrastructure/  # Repositorios y seguridad (JWT)
│   │   │   └── application/     # Servicios de negocio (UserDetailsServiceImpl)
│   │   ├── categoria/           # CRUD de categorías
│   │   └── producto/            # CRUD de productos
│   └── shared/
│       └── exception/           # Manejo de excepciones globales y personalizadas
└── src/main/resources/
    ├── application.properties
    └── templates, static

⚙️ Tecnologías utilizadas

Java 17

Spring Boot 3.x

Spring Security + JWT

Spring Data JPA

PostgreSQL

Lombok para reducir boilerplate

Maven como gestor de dependencias

🔹 Módulos principales
1️⃣ Módulo Auth

UserDetailsServiceImpl: Maneja usuarios, roles y permisos.

Autenticación JWT: Genera tokens y valida acceso.

Roles y permisos: Flexible para controlar endpoints.

Excepciones controladas: UnauthorizedException, BadRequestException.

2️⃣ Módulo Categoría

CRUD completo de categorías con DTOs (CategoriaCreateDTO, CategoriaResponseDTO).

Eliminación lógica (activa = false).

Validaciones:

Nombre único.

Existencia de categoría para actualización/eliminación.

Excepciones manejadas: ResourceNotFoundException, BadRequestException.

3️⃣ Módulo Producto

CRUD completo de productos con relación a categorías.

Eliminación lógica (activo = false).

Validaciones:

Producto y categoría existen antes de crear o actualizar.

Excepciones manejadas: ResourceNotFoundException, BadRequestException.

4️⃣ Shared / Excepciones

GlobalExceptionHandler: Centraliza errores y genera respuestas JSON consistentes.

Tipos de excepciones:

ResourceNotFoundException → 404

BadRequestException → 400

UnauthorizedException → 401

BusinessException → 422

Formato de error uniforme (ErrorResponse).

🛠 Funcionalidades principales
Funcionalidad	Endpoint	Método	Descripción
Crear usuario	/api/auth/users	POST	Crea usuario con roles y genera JWT
Login	/api/auth/login	POST	Autentica usuario y genera JWT
Listar categorías	/api/categorias	GET	Obtiene todas las categorías activas
Crear categoría	/api/categorias	POST	Crea nueva categoría
Actualizar categoría	/api/categorias/{id}	PUT	Actualiza datos de categoría existente
Eliminar categoría	/api/categorias/{id}	DELETE	Eliminación lógica
Listar productos	/api/productos	GET	Obtiene todos los productos activos
Crear producto	/api/productos	POST	Crea producto asociado a categoría
Actualizar producto	/api/productos/{id}	PUT	Actualiza producto existente
Eliminar producto	/api/productos/{id}	DELETE	Eliminación lógica de producto

Todos los endpoints devuelven respuestas JSON consistentes y controlan errores mediante GlobalExceptionHandler.

💡 Buenas prácticas aplicadas

Arquitectura modular: modules por contexto de negocio.

Separación de capas:

Controller → solo HTTP y validaciones básicas.

Service → lógica de negocio y transacciones.

Repository → acceso a DB.

Uso de DTOs para desacoplar entidad ↔ API.

Transacciones con @Transactional para consistencia.

JWT stateless para autenticación segura.

Excepciones centralizadas y uniformes.

Validaciones y unicidad de datos.

🏗 Próximos pasos / mejoras posibles

Integrar validaciones automáticas (@Valid) en DTOs.

Implementar logs centralizados con SLF4J / Logback.

Agregar tests unitarios e integración (JUnit + Mockito + MockMvc).

Refactorizar mapeo DTO ↔ Entity con MapStruct.

Mejorar seguridad JWT: capturar tokens expirados o inválidos.

Auditar cambios: agregar fechaActualizacion y usuarioModificador.

⚡ Cómo ejecutar
# Clonar repositorio
git clone <REPO_URL>
cd crudstore-backend

# Compilar y ejecutar
mvn clean install
mvn spring-boot:run

# La aplicación correrá en http://localhost:8080


Frontend sugerido: Angular (http://localhost:4200
) para integración con CORS configurado.

🔐 Seguridad

Endpoints /auth/** → públicos para login y registro.

Otros endpoints → protegidos mediante JWT + roles.

JWT validado por JWTokenValidator antes de ejecutar controllers.

📦 Dependencias principales

spring-boot-starter-web

spring-boot-starter-data-jpa

spring-boot-starter-security

spring-boot-starter-validation

spring-boot-starter-test

lombok

jjwt (JSON Web Tokens)
