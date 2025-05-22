# 📚 Spring Boot Cheat Sheet (Implementation-Focused)

A hands-on, no-fluff guide to building and deploying Spring Boot apps — from models to Docker 🚀

---

## 📖 Table of Contents

- [🚀 Getting Started with Spring Boot](#-getting-started-with-spring-boot)
- [🧱 Models / Entities](#-models--entities)
- [🔖 Annotations for Models](#-annotations-for-models)
- [🔄 Relationships](#-relationships)
- [📂 Repositories](#-repositories)
- [🔌 Common Interfaces](#-common-interfaces)
- [🧠 Custom Queries](#-custom-queries)
- [🧠 Services](#-services)
- [🎮 Controllers](#-controllers)
- [📦 DTOs (Data Transfer Objects)](#-dtos-data-transfer-objects)
- [🌐 application.properties / application.yml](#-applicationproperties--applicationyml)
- [🗃️ Database Integration (JPA + SQL)](#️-database-integration-jpa--sql)
- [🔒 Spring Security (Optional)](#-spring-security-optional)
- [🧪 Testing](#-testing)
- [🧰 Dev Tools & Extras](#-dev-tools--extras)
- [🐳 Dockerizing Spring Boot](#-dockerizing-spring-boot)
- [🚀 Deployment](#-deployment)

---

## 🚀 Getting Started with Spring Boot

| Task              | Tool/Command/Explanation                                 |
|-------------------|----------------------------------------------------------|
| Initialize project| [start.spring.io](https://start.spring.io/) (Spring Initializr) |
| Build tool        | Use Maven or Gradle                                      |
| Main class        | Annotated with `@SpringBootApplication`                  |
| Run project       | `./mvnw spring-boot:run` or `java -jar target/app.jar`   |

**Main Class Example:**
```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

---

## 🧱 Models / Entities

Represent database tables using annotated Java classes.

---

## 🔖 Annotations for Models

| Annotation         | Purpose                             |
|--------------------|-------------------------------------|
| `@Entity`          | Marks class as a JPA entity         |
| `@Table(name="...")`| Map to a custom table name         |
| `@Id`              | Declares primary key                |
| `@GeneratedValue`  | Auto-generates ID (AUTO, IDENTITY)  |
| `@Column`          | Customizes column mapping           |

---

## 🔄 Relationships

| Annotation     | Use Case                              |
|----------------|----------------------------------------|
| `@OneToMany`   | One parent → many children             |
| `@ManyToOne`   | Many children → one parent             |
| `@OneToOne`    | One-to-one relationship                |
| `@ManyToMany`  | Many-to-many relationship              |
| `mappedBy`     | Reference owning side of relationship  |

---

## 📂 Repositories

Spring Data JPA interfaces that handle database interactions.

---

## 🔌 Common Interfaces

| Interface                | Description                                |
|--------------------------|--------------------------------------------|
| `JpaRepository<T, ID>`   | Full CRUD + pagination & sorting           |
| `CrudRepository<T, ID>`  | Basic CRUD only                            |

---

## 🧠 Custom Queries

**Method Name Query:**
```java
List<User> findByLastName(String lastName);
```

**JPQL Query with Annotation:**
```java
@Query("SELECT u FROM User u WHERE u.email = ?1")
User findByEmail(String email);
```

### Common Annotations

| Annotation     | Purpose                             |
|----------------|-------------------------------------|
| `@Repository`  | Marks the interface for JPA          |
| `@Transactional`| Ensures atomic DB operations        |

---

## 🧠 Services

Contains business logic and connects to repositories.

**Service Example:**
```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
```

| Annotation     | Purpose                        |
|----------------|--------------------------------|
| `@Service`     | Declares a service component   |
| `@Transactional`| Optional for transaction control |

---

## 🎮 Controllers

Define your REST API.

```java
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
```

---

## 🔖 Common Annotations for Controllers

| Annotation         | Usage                                      |
|--------------------|--------------------------------------------|
| `@RestController`  | REST controller with auto-response body    |
| `@RequestMapping`  | Base route path (e.g., /api)               |
| `@GetMapping`      | Maps GET requests                          |
| `@PostMapping`     | Maps POST requests                         |
| `@PutMapping`      | Maps PUT requests                          |
| `@DeleteMapping`   | Maps DELETE requests                       |
| `@RequestBody`     | Binds request JSON to object               |
| `@PathVariable`    | Extracts path variable                     |
| `@RequestParam`    | Extracts query param from URL              |

---

## 📦 DTOs (Data Transfer Objects)

Use DTOs to shape API inputs/outputs and add validation.

**Example DTO:**
```java
public class UserDTO {
    @NotBlank
    private String name;

    @Email
    private String email;
}
```

### DTO Annotations

| Annotation     | Purpose                               |
|----------------|----------------------------------------|
| `@NotBlank`    | Field must not be null/empty           |
| `@Email`       | Must be valid email format             |
| `@Valid`       | Enables validation in controller       |

---

## 🌐 application.properties / application.yml

**Common Settings:**
```properties
# Server
server.port=8081

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=root
spring.datasource.password=secret

# JPA
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

---

## 🗃️ Database Integration (JPA + SQL)

- Add MySQL/PostgreSQL/H2 dependency
- Configure in `application.properties`

**Optional files:**
- `schema.sql` — auto-executed for schema setup
- `data.sql` — auto-executed for seed data

---

## 🔒 Spring Security (Optional)

**Basic Config:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic();
    }
}
```

| Annotation         | Purpose                         |
|--------------------|----------------------------------|
| `@EnableWebSecurity`| Enable Spring Security features |
| `@PreAuthorize`     | Role-based method authorization |

---

## 🧪 Testing

| Test Type        | Annotation         | Use Case                          |
|------------------|--------------------|------------------------------------|
| Unit Test        | `@WebMvcTest`      | Controller-level testing           |
| JPA Test         | `@DataJpaTest`     | Repository layer testing           |
| Integration Test | `@SpringBootTest`  | Full app and API testing           |

**Mock Example:**
```java
@MockBean
private UserService userService;
```

---

## 🧰 Dev Tools & Extras

| Tool/Feature       | Description                                |
|--------------------|--------------------------------------------|
| DevTools           | Auto-restarts app on code changes          |
| Swagger / OpenAPI  | API docs interface                         |
| `@ControllerAdvice`| Global exception handler                   |
| SLF4J / Logback    | Standard logging frameworks                |

---

## 🐳 Dockerizing Spring Boot

**Dockerfile:**
```dockerfile
FROM openjdk:17
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Build & Run:**
```bash
docker build -t springboot-app .
docker run -p 8080:8080 springboot-app
```

---

## 🚀 Deployment

| Task             | How to Do It                                 |
|------------------|-----------------------------------------------|
| Build JAR        | `./mvnw clean package`                        |
| Run JAR          | `java -jar target/app.jar`                    |
| Env Variables    | `--server.port=8082`, `.env`, cloud configs   |
| Deploy Options   | Heroku, Railway, Render, EC2, Docker container|

---
