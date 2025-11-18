## Spring Security Service

Spring Boot 3.5 application that demonstrates role–based access control, JWT authentication, and Swagger-powered documentation. It exposes registration/authentication endpoints plus secured admin/user APIs that rely on fine-grained permission checks.

---

### Features

- JWT authentication with a stateless security filter chain.
- Custom `JwtAuthFilter` for token extraction, validation, and security context population.
- Role/permission repositories with a `PermissionInitializer` seeding default authorities.
- Exposed CRUD-style admin routes protected by `hasAuthority` rules.
- Swagger UI (`springdoc-openapi`) with Bearer auth support for testing.

---

### Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL running with a database matching the credentials in `src/main/resources/application.properties`

---

### Configuration

Update the following properties to match your environment:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/security
spring.datasource.username=elhassen
spring.datasource.password=****
```

The JWT secret is defined inside `JwtService`. For production, externalize it via properties or environment variables and rotate it periodically.

---

### Build & Run

```bash
mvn clean package
mvn spring-boot:run
```

The service starts on `http://localhost:9090`.

---

### Authentication Flow

1. **Register** via `POST /auth/register` to create a user (password gets encoded, default role assigned).
2. **Login** via `POST /auth/login` with JSON:
   ```json
   { "username": "admin", "password": "secret" }
   ```
   The response contains a JWT token.
3. For secured endpoints, add `Authorization: Bearer <token>` header.

---

### Swagger & Testing

- Swagger UI: `http://localhost:9090/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:9090/v3/api-docs`

In Swagger UI:
1. Authenticate using `/auth/login`.
2. Click **Authorize**, supply `Bearer <token>`.
3. Call protected endpoints such as `/student/call` or admin routes; 403 indicates missing permissions.

---

### Key Endpoints

| Method | Path                       | Access                          | Description                      |
|--------|---------------------------|---------------------------------|----------------------------------|
| POST   | `/auth/register`          | public                          | Create a new user                |
| POST   | `/auth/login`             | public                          | Obtain JWT token                 |
| GET    | `/student/call`           | authenticated                   | Sample protected resource        |
| GET    | `/admin/roles`            | `list_role` authority           | List available roles             |
| POST   | `/admin/roles`            | `create_role` authority         | Create a role                    |
| GET    | `/admin/permissions`      | `list_permission` authority     | List permissions                 |
| GET    | `/user/profile`           | `get_profile` authority         | Current user profile             |

Refer to `SecurityConfig` for the full matcher list.

---

### Testing

`StudentController` and other secured controllers can be hit with tools such as curl or Postman:

```bash
curl -H "Authorization: Bearer <token>" http://localhost:9090/student/call
```

Run unit tests with `mvn test`.

---

### Future Improvements

- Externalize JWT configuration (secret, issuer, TTL).
- Add refresh tokens or revocation support.
- Introduce DTO validation and error handling patterns.
- Expand test coverage for security rules and controllers.

