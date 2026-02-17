package com.mglopez.crudstore.config;

import com.mglopez.crudstore.modules.auth.domain.PermissionEntity;
import com.mglopez.crudstore.modules.auth.domain.RoleEntity;
import com.mglopez.crudstore.modules.auth.domain.RoleEnum;
import com.mglopez.crudstore.modules.auth.domain.UserEntity;
import com.mglopez.crudstore.modules.auth.infrastructure.repositories.PermissionRepository;
import com.mglopez.crudstore.modules.auth.infrastructure.repositories.RoleRepository;
import com.mglopez.crudstore.modules.auth.infrastructure.repositories.UserRepository;
import com.mglopez.crudstore.modules.categoria.api.dtos.CategoriaCreateDTO;
import com.mglopez.crudstore.modules.categoria.application.CategoriaService;
import com.mglopez.crudstore.modules.producto.api.dtos.ProductoCreateDTO;
import com.mglopez.crudstore.modules.producto.application.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * ------------------------------------------------------------------
 * CONFIGURACIÓN INICIAL DE DATOS (DataInitializer)
 * ------------------------------------------------------------------
 * Este componente se ejecuta al iniciar la aplicación y permite:
 *  - Crear categorías y productos iniciales
 *  - Crear permisos (READ, CREATE, UPDATE, DELETE)
 *  - Crear roles y asociarles permisos
 *  - Crear usuarios con roles asignados
 *
 * Útil para:
 *  - Entorno de desarrollo
 *  - Pruebas iniciales
 *  - Evitar tener que insertar datos manualmente
 *
 * NOTA:
 *  - Se usa CommandLineRunner con un Bean para ejecución automática
 *  - Passwords de usuarios codificados con PasswordEncoder
 * ------------------------------------------------------------------
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            CategoriaService categoriaService,
            ProductoService productoService,
            PermissionRepository permissionRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // ======================
            // CATEGORÍAS / PRODUCTOS
            // ======================
            // Creación de categorías de ejemplo
            var cat1 = categoriaService.crearCategoria(
                    new CategoriaCreateDTO("Electrónica", "Dispositivos electrónicos"));

            var cat2 = categoriaService.crearCategoria(
                    new CategoriaCreateDTO("Hogar", "Productos para el hogar"));

            var cat3 = categoriaService.crearCategoria(
                    new CategoriaCreateDTO("Ropa", "Ropa y accesorios"));

            // Creación de productos iniciales
            productoService.crearProducto(
                    new ProductoCreateDTO("Laptop Gamer", "Laptop con RTX 4060",
                            new BigDecimal("1299.99"), cat1.id())
            );

            productoService.crearProducto(
                    new ProductoCreateDTO("Aspiradora", "Aspiradora inteligente",
                            new BigDecimal("299.99"), cat2.id())
            );

            productoService.crearProducto(
                    new ProductoCreateDTO("Chaqueta", "Chaqueta impermeable",
                            new BigDecimal("89.99"), cat3.id())
            );

            // ======================
            // PERMISOS
            // ======================
            // Creación de permisos básicos
            PermissionEntity read = permissionRepository.save(
                    PermissionEntity.builder().name("READ").build()
            );
            PermissionEntity create = permissionRepository.save(
                    PermissionEntity.builder().name("CREATE").build()
            );
            PermissionEntity update = permissionRepository.save(
                    PermissionEntity.builder().name("UPDATE").build()
            );
            PermissionEntity delete = permissionRepository.save(
                    PermissionEntity.builder().name("DELETE").build()
            );

            // ======================
            // ROLES
            // ======================
            // Creación de roles y asignación de permisos correspondientes
            RoleEntity adminRole = roleRepository.save(
                    RoleEntity.builder()
                            .roleEnum(RoleEnum.ADMIN)
                            .permissionsList(Set.of(read, create, update, delete))
                            .build()
            );

            RoleEntity userRole = roleRepository.save(
                    RoleEntity.builder()
                            .roleEnum(RoleEnum.USER)
                            .permissionsList(Set.of(read, create))
                            .build()
            );

            RoleEntity devRole = roleRepository.save(
                    RoleEntity.builder()
                            .roleEnum(RoleEnum.DEVELOPER)
                            .permissionsList(Set.of(read, create, update))
                            .build()
            );

            RoleEntity invitedRole = roleRepository.save(
                    RoleEntity.builder()
                            .roleEnum(RoleEnum.INVITED)
                            .permissionsList(Set.of(read))
                            .build()
            );

            // ======================
            // USUARIOS
            // ======================
            // Creación de usuarios iniciales con roles asignados
            userRepository.saveAll(List.of(
                    UserEntity.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin123")) // codificación segura
                            .isEnabled(true)
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .roles(Set.of(adminRole))
                            .build(),

                    UserEntity.builder()
                            .username("user")
                            .password(passwordEncoder.encode("user123")) // codificación segura
                            .isEnabled(true)
                            .accountNonExpired(true)
                            .accountNonLocked(true)
                            .credentialsNonExpired(true)
                            .roles(Set.of(userRole))
                            .build()
            ));
        };
    }
}
