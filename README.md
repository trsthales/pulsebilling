# PulseBilling

> **Languages:** **English** | [🇧🇷 Português](README.pt-BR.md)

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

**PulseBilling** is an enterprise-grade reference application for subscription management and recurring billing. It is designed following the tenets of **Clean Architecture**, **Domain-Driven Design (DDD)**, **SOLID principles**, and **Test-Driven Development (TDD)**.

This repository serves as open-source technical reference code accompanying the technical articles at [thalesouza.com.br](https://thalesouza.com.br).

---

## 🏛️ Architecture & Package Layout

The codebase strictly adheres to Hexagonal / Clean Architecture boundaries:

```text
com.pulsebilling/
├── domain/                  # Enterprise business rules, Aggregates, Value Objects, Domain Events
│                            # ⚠️ ZERO dependencies on frameworks (Spring, JPA, etc.)
├── application/             # Application business rules, Use Cases, Inbound & Outbound Ports
├── infrastructure/          # Technical adapters: Spring Data JPA repositories, Redis configs, HTTP clients
└── presentation/            # Driving adapters: REST Controllers, Request/Response DTOs, Exception Handlers
```

### Architectural Principles
- **Independence of Frameworks:** The core business rules in `domain` and `application` do not depend on the existence of any library or framework.
- **Testability:** Business rules can be tested without the UI, database, web server, or any other external element.
- **Independence of UI & Database:** Switching databases (e.g., PostgreSQL to another engine) or presentation protocols (REST, GraphQL, gRPC) does not affect domain logic.

---

## 🚀 Quickstart Guide

Get up and running in **3 commands**:

### 1. Start Infrastructure (PostgreSQL 16 & Redis 7)
```bash
docker compose up -d
```

### 2. Run Test Suite
```bash
./mvnw clean test
```

### 3. Launch Application
```bash
./mvnw spring-boot:run
```

Once started, the application will be listening on `http://localhost:8080`.
Actuator health check is available at:
```bash
curl http://localhost:8080/actuator/health
```

---

## ⚙️ Environment Configuration

| Setting | Default Value | Description |
|---|---|---|
| PostgreSQL URL | `jdbc:postgresql://localhost:5432/pulsebilling_db` | Main application database |
| Database User / Password | `pulsebilling` / `pulsebilling` | Default credentials for local Docker Compose |
| Redis Host / Port | `localhost:6379` | Cache and distributed lock provider |
| Server Port | `8080` | HTTP API port |

### Profiles
- **`default` (`application.yml`):** Targets the local Docker environment with PostgreSQL, Redis, and Flyway database migrations enabled.
- **`test` (`application-test.yml`):** Configured for isolated, fast execution of unit and integration tests using an in-memory database without external service dependencies.

---

## 🛠️ Tech Stack & Key Libraries

- **Language:** Java 17 (OpenJDK)
- **Framework:** Spring Boot 3.3.x
  - `spring-boot-starter-web` (REST APIs)
  - `spring-boot-starter-data-jpa` (Hibernate 6 persistence)
  - `spring-boot-starter-data-redis` (Redis client)
  - `spring-boot-starter-validation` (Jakarta Bean Validation)
  - `spring-boot-starter-actuator` (Production-ready monitoring)
- **Database & Migrations:** PostgreSQL 16, Flyway Migration (`flyway-core`, `flyway-database-postgresql`)
- **Testing:** JUnit 5, Mockito, AssertJ, H2 (for isolated in-memory test profiles)
- **Productivity:** Project Lombok

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
