CrudAppStore-SpringSecurity-JWT-v1

App centrada en el manejo de seguridad con JWT.

⚠️ Nota: El objetivo del proyecto es el manejo de Spring Security + JWT. Para probarlo, ejecuta solo el backend y utiliza Postman.

🛒 CRUDStore Backend

Backend modular y escalable para un sistema de gestión de productos y categorías, con autenticación JWT y roles de usuario.

📋 Descripción

CRUDStore Backend es un proyecto profesional que implementa:

Gestión de productos y categorías (CRUD completo con eliminación lógica).

Sistema de autenticación y autorización con Spring Security y JWT.

Roles y permisos flexibles para controlar acceso.

Excepciones globales para respuestas uniformes en la API.

Arquitectura modular, limpia y fácil de mantener.

Ideal para aprender buenas prácticas de Spring Boot, DDD ligero, manejo de JWT y diseño de APIs robustas.

~~~
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
~~~

| Tecnología            | Versión/Detalle           |
| --------------------- | ------------------------- |
| Java                  | 17                        |
| Spring Boot           | 3.x                       |
| Spring Security + JWT | -                         |
| Spring Data JPA       | -                         |
| Base de datos         | PostgreSQL (configurable) |
| Lombok                | Reduce boilerplate        |
| Maven                 | Gestor de dependencias    |


| Módulo        | Funcionalidad                                                                                                                                                                                                                                         |
| ------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Auth**      | - Manejo de usuarios, roles y permisos<br>- Autenticación JWT<br>- Excepciones: `UnauthorizedException`, `BadRequestException`                                                                                                                        |
| **Categoría** | - CRUD de categorías<br>- Eliminación lógica (`activa = false`)<br>- Validaciones de nombre único y existencia<br>- Excepciones: `ResourceNotFoundException`, `BadRequestException`                                                                   |
| **Producto**  | - CRUD de productos con relación a categorías<br>- Eliminación lógica (`activo = false`)<br>- Validaciones de existencia de producto y categoría<br>- Excepciones: `ResourceNotFoundException`, `BadRequestException`                                 |
| **Shared**    | - GlobalExceptionHandler para respuestas uniformes<br>- Tipos de excepción: `ResourceNotFoundException` (404), `BadRequestException` (400), `UnauthorizedException` (401), `BusinessException` (422)<br>- Formato de error uniforme (`ErrorResponse`) |


| Funcionalidad        | Endpoint               | Método | Descripción                            |
| -------------------- | ---------------------- | ------ | -------------------------------------- |
| Crear usuario        | `/api/auth/users`      | POST   | Crea usuario con roles y genera JWT    |
| Login                | `/api/auth/login`      | POST   | Autentica usuario y genera JWT         |
| Listar categorías    | `/api/categorias`      | GET    | Obtiene todas las categorías activas   |
| Crear categoría      | `/api/categorias`      | POST   | Crea nueva categoría                   |
| Actualizar categoría | `/api/categorias/{id}` | PUT    | Actualiza datos de categoría existente |
| Eliminar categoría   | `/api/categorias/{id}` | DELETE | Eliminación lógica                     |
| Listar productos     | `/api/productos`       | GET    | Obtiene todos los productos activos    |
| Crear producto       | `/api/productos`       | POST   | Crea producto asociado a categoría     |
| Actualizar producto  | `/api/productos/{id}`  | PUT    | Actualiza producto existente           |
| Eliminar producto    | `/api/productos/{id}`  | DELETE | Eliminación lógica de producto         |

Todos los endpoints devuelven respuestas JSON consistentes y controlan errores mediante GlobalExceptionHandler.

💡 Buenas prácticas aplicadas

Arquitectura modular (modules por contexto de negocio).

Separación de capas:

Controller → solo HTTP y validaciones básicas.

Service → lógica de negocio y transacciones.

Repository → acceso a DB.

Uso de DTOs para desacoplar entidad ↔ API.

Transacciones con @Transactional para consistencia.

JWT stateless para autenticación segura.

Excepciones centralizadas y uniformes.

Validaciones y unicidad de datos.

🏗 Próximos pasos / mejoras

Integrar validaciones automáticas (@Valid) en DTOs.

Implementar logs centralizados con SLF4J / Logback.

Agregar tests unitarios e integración (JUnit + Mockito + MockMvc).

Refactorizar mapeo DTO ↔ Entity con MapStruct.

Mejorar seguridad JWT: capturar tokens expirados o inválidos.

Auditar cambios: agregar fechaActualizacion y usuarioModificador.

# Clonar repositorio
git clone <REPO_URL>
cd crudstore-backend

# Compilar y ejecutar
mvn clean install
mvn spring-boot:run

# La aplicación correrá en http://localhost:9525
Frontend sugerido: Angular (http://localhost:4200) para integración con CORS configurado.

| Endpoint        | Acceso                                                        |
| --------------- | ------------------------------------------------------------- |
| `/auth/**`      | Público (login y registro)                                    |
| Otros endpoints | Protegidos mediante JWT + roles                               |
| JWT             | Validado por `JWTokenValidator` antes de ejecutar controllers |

| Dependencia                    |
| ------------------------------ |
| spring-boot-starter-web        |
| spring-boot-starter-data-jpa   |
| spring-boot-starter-security   |
| spring-boot-starter-validation |
| lombok                         |
| jjwt                           |


