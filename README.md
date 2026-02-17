<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java 17](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

Una API REST robusta y segura para gestión de tienda con autenticación JWT y control de acceso por roles

🚀 **Características •**  
📚 **Documentación API •**  
🔐 **Seguridad •**  
⚙️ **Instalación**

</div>


📋 **Tabla de Contenido**

1. 🎯 Descripción General  
2. ✨ Características Principales  
3. 🏗️ Arquitectura del Proyecto  
4. 💻 Tecnologías Utilizadas  
5. 🔐 Modelo de Seguridad  
6. 🛠️ API Endpoints  
7. 📂 Estructura de Archivos  
8. 🗄️ Base de Datos  
9. ⚙️ Instalación y Ejecución  
10. ⚠️ Manejo de Excepciones  
11. 🌟 Buenas Prácticas  
12. 👥 Autores


<div align="center">

🎯 **Descripción General**

CRUD Store es una aplicación **backend** desarrollada con **Spring Boot** que proporciona una **API REST completa** para la gestión de un **catálogo de productos** organizados por categorías.  

El sistema implementa un **modelo de seguridad avanzado** basado en **JWT (JSON Web Tokens)** con un sistema **granular de roles y permisos**.

</div>

![Arquitectura del Proyecto](docs/DiagramaArquitectura.png)

<div align="center">

✨ **Características Principales**

| Funcionalidad        | Estado    | Descripción                                     |
|---------------------|-----------|-------------------------------------------------|
| ✅ CRUD Productos    | Completo  | Gestión completa de productos con soft delete |
| ✅ CRUD Categorías   | Completo  | Organización de productos por categorías      |
| ✅ Autenticación JWT | Completo  | Sistema seguro basado en tokens               |
| ✅ Roles y Permisos  | Completo  | Control de acceso granular (RBAC)            |
| ✅ Validaciones      | Completo  | Validación en todas las capas                 |
| ✅ Documentación     | Completo  | Código autodocumentado y README               |
| ✅ Soft Delete       | Completo  | Eliminación lógica para preservar datos       |
| ✅ CORS Configurado  | Completo  | Comunicación con frontend Angular             |

</div>


🏗️ **Arquitectura del Proyecto**
El proyecto sigue una arquitectura hexagonal (puertos y adaptadores) organizada en módulos funcionales:


~~~
┌─────────────────────────────────────────────────────────────┐
│                       🌐 API LAYER                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │ Controllers  │  │     DTOs     │  │  Validation  │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
├─────────────────────────────────────────────────────────────┤
│                    ⚙️ APPLICATION LAYER                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Services   │  │   Use Cases  │  │  Exceptions  │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
├─────────────────────────────────────────────────────────────┤
│                      📦 DOMAIN LAYER                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Entities   │  │    Enums     │  │  Repository  │      │
│  │              │  │              │  │  Interfaces  │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
├─────────────────────────────────────────────────────────────┤
│                 🔧 INFRASTRUCTURE LAYER                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │ Repositories │  │   Security   │  │  JPA/Hibernate│      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
└─────────────────────────────────────────────────────────────┘
~~~

📁 **Estructura por Módulos**
~~~
📦 crudstore-backend/
├── 📁 src/main/java/com/mglopez/crudstore/
│   ├── 📁 config/                    # Configuraciones globales
│   │   ├── 📄 CorsConfig.java        # Configuración CORS
│   │   └── 📄 DataInitializer.java   # Datos iniciales automáticos
│   ├── 📁 modules/                    # Módulos funcionales
│   │   ├── 📁 auth/                    # 🔐 Módulo de autenticación
│   │   ├── 📁 categoria/                # 📂 Módulo de categorías
│   │   └── 📁 producto/                 # 📦 Módulo de productos
│   └── 📁 shared/                      # Código compartido
│       └── 📁 exception/                # Manejo global de errores
└── 📁 src/main/resources/
    ├── 📄 application.properties       # Configuración de la app
    └── 📁 exception/                    # Clases de excepción
~~~

💻 **Tecnologías Utilizadas**
<div align="center">

🛠 **Tecnologías Utilizadas**

| Tecnología          | Versión  | Icono | Propósito                    |
|--------------------|----------|-------|-------------------------------|
| Java               | 17       | ![Java](https://img.shields.io/badge/Java-17-blue) | Lenguaje principal          |
| Spring Boot        | 4.0.2    | ![Spring Boot](https://img.shields.io/badge/Spring-4.0.2-brightgreen) | Framework base              |
| Spring Security    | 6.x      | ![Spring Security](https://img.shields.io/badge/Security-6.x-green) | Autenticación               |
| Spring Data JPA    | 3.x      | ![Spring Data JPA](https://img.shields.io/badge/JPA-3.x-yellow) | Persistencia                |
| JWT (Auth0)        | 4.5.0    | ![JWT](https://img.shields.io/badge/JWT-4.5.0-orange) | Tokens                      |
| PostgreSQL         | 15.x     | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.x-blue) | Base de datos               |
| Lombok             | 1.18.30  | ![Lombok](https://img.shields.io/badge/Lombok-1.18.30-red) | Código limpio               |
| Maven              | 3.8.x    | ![Maven](https://img.shields.io/badge/Maven-3.8.x-purple) | Build tool                  |
| Bean Validation    | 3.x      | ![Validation](https://img.shields.io/badge/Validation-3.x-lightgrey) | Validaciones               |

</div>

📦 **Dependencias Principales (pom.xml)**

~~~
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- JWT -->
    <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>4.5.0</version>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
~~~

🔐 **Modelo de Seguridad**
El sistema implementa un modelo de seguridad RBAC (Role-Based Access Control) con permisos granulares:

![Modelo de Seguridad](docs/diagramaSecurityRoles.png)

📊 **Matriz de Acceso**
<div align="center">

📊 **Matriz de Acceso**

| Rol       | READ | CREATE | UPDATE | DELETE |
|-----------|------|--------|--------|--------|
| ADMIN     | ✅   | ✅     | ✅     | ✅     |
| USER      | ✅   | ✅     | ❌     | ❌     |
| DEVELOPER | ✅   | ✅     | ✅     | ❌     |
| INVITED   | ✅   | ❌     | ❌     | ❌     |

</div>

🔄 Flujo de Autenticación
![Flujo Securityt](docs/flujo_security.png)





