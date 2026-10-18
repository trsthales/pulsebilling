# PulseBilling

> **Idiomas:** [🇺🇸 English](README.md) | **🇧🇷 Português**

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

**PulseBilling** é uma aplicação de referência corporativa para gestão de assinaturas e faturamento recorrente. Desenvolvida seguindo os princípios de **Clean Architecture**, **Domain-Driven Design (DDD)**, **SOLID** e **Test-Driven Development (TDD)**.

Este repositório serve como código aberto de referência técnica que acompanha a trilha de artigos em [thalesouza.com.br](https://thalesouza.com.br).

---

## 🏛️ Arquitetura e Estrutura de Pacotes

A base de código adere rigorosamente aos limites da Arquitetura Limpa / Hexagonal:

```text
com.pulsebilling/
├── domain/                  # Regras de negócio corporativas, Agregados, Value Objects, Eventos de Domínio
│                            # ⚠️ ZERO dependências de frameworks (Spring, JPA, etc.)
├── application/             # Regras de negócio da aplicação, Casos de Uso, Portas Inbound e Outbound
├── infrastructure/          # Adaptadores técnicos: Repositórios Spring Data JPA, configs do Redis, clientes HTTP
└── presentation/            # Adaptadores primários: Controllers REST, DTOs de Request/Response, Tratamento de Erros
```

### Princípios Arquiteturais
- **Independência de Frameworks:** As regras de negócio centrais em `domain` e `application` não dependem da existência de bibliotecas ou frameworks externos.
- **Testabilidade:** As regras de negócio podem ser testadas sem interface de usuário, banco de dados, servidor web ou qualquer outro elemento externo.
- **Independência de UI e Banco de Dados:** Mudar de banco de dados (ex.: migrar de PostgreSQL para outro mecanismo) ou protocolos de apresentação (REST, GraphQL, gRPC) não impacta a lógica de domínio.

---

## 🚀 Guia de Início Rápido

Inicie a aplicação em **3 comandos**:

### 1. Subir a Infraestrutura (PostgreSQL 16 e Redis 7)
```bash
docker compose up -d
```

### 2. Executar a Suíte de Testes
```bash
./mvnw clean test
```

### 3. Executar a Aplicação
```bash
./mvnw spring-boot:run
```

Após a inicialização, a aplicação estará disponível em `http://localhost:8080`.
O health check do Actuator pode ser consultado via:
```bash
curl http://localhost:8080/actuator/health
```

---

## ⚙️ Configuração de Ambiente

| Configuração | Valor Padrão | Descrição |
|---|---|---|
| URL do PostgreSQL | `jdbc:postgresql://localhost:5432/pulsebilling_db` | Banco de dados principal da aplicação |
| Usuário / Senha do BD | `pulsebilling` / `pulsebilling` | Credenciais padrão do Docker Compose local |
| Host / Porta do Redis | `localhost:6379` | Provedor de cache e locks distribuídos |
| Porta do Servidor | `8080` | Porta da API HTTP |

### Perfis (Profiles)
- **`default` (`application.yml`):** Aponta para o ambiente Docker local com PostgreSQL, Redis e migrações do Flyway ativadas.
- **`test` (`application-test.yml`):** Configurado para execução rápida e isolada de testes unitários e de integração utilizando banco de dados em memória, sem dependência de serviços externos.

---

## 🛠️ Stack Tecnológica e Bibliotecas Principais

- **Linguagem:** Java 17 (OpenJDK)
- **Framework:** Spring Boot 3.3.x
  - `spring-boot-starter-web` (APIs REST)
  - `spring-boot-starter-data-jpa` (Persistência com Hibernate 6)
  - `spring-boot-starter-data-redis` (Cliente Redis)
  - `spring-boot-starter-validation` (Validação Jakarta Bean Validation)
  - `spring-boot-starter-actuator` (Monitoramento e métricas de produção)
- **Banco de Dados e Migrações:** PostgreSQL 16, Migrações com Flyway (`flyway-core`, `flyway-database-postgresql`)
- **Testes:** JUnit 5, Mockito, AssertJ, H2 (para perfil de testes isolados em memória)
- **Produtividade:** Project Lombok

---

## 📄 Licença
Este projeto é de código aberto e está disponível sob a [Licença MIT](LICENSE).
