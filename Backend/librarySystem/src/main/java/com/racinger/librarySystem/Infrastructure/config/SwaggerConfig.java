package com.racinger.librarySystem.Infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🏛️ Library Management System API")
                        .version("1.0.0")
                        .description("""
                            # Sistema de Gestión de Biblioteca

                            API REST completa para la gestión integral de una biblioteca universitaria.

                            ## 📚 Funcionalidades Principales

                            - **📖 Gestión de Libros**: CRUD completo con autores y categorías
                            - **👥 Gestión de Estudiantes**: Registro y administración de usuarios
                            - **📋 Sistema de Préstamos**: Control de préstamos y devoluciones
                            - **⏰ Reservas**: Sistema de reservas de libros
                            - **💰 Penalizaciones**: Gestión de multas por retrasos
                            - **📊 Reportes**: Estadísticas y reportes del sistema

                            ## 🔐 Autenticación
                            Actualmente sin autenticación implementada (para desarrollo).

                            ## 📋 Estados de Préstamos
                            - `ACTIVE`: Préstamo activo
                            - `RETURNED`: Libro devuelto
                            - `OVERDUE`: Préstamo vencido

                            ## 📋 Estados de Reservas
                            - `ACTIVE`: Reserva activa
                            - `CANCELLED`: Reserva cancelada
                            - `EXPIRED`: Reserva expirada
                            - `FULFILLED`: Reserva completada

                            ## 📋 Estados de Penalizaciones
                            - `PENDING`: Penalización pendiente
                            - `PAID`: Penalización pagada
                            - `CANCELLED`: Penalización cancelada
                            """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo - Racinger")
                                .email("racinger@librarysystem.com")
                                .url("https://github.com/racinger/library-system"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                    new Server().url("http://localhost:8081").description("Servidor de Desarrollo"),
                    new Server().url("https://api.librarysystem.com").description("Servidor de Producción")
                ))
                .components(new Components()
                    .addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .description("JWT Authorization header using the Bearer scheme. Example: \"Authorization: Bearer {token}\"")
                    )
                )
                .security(List.of(
                    new SecurityRequirement().addList("bearerAuth")
                ));
    }
}