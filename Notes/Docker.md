# 🐳 Docker Essentials Cheat Sheet

Everything you need to know to start using Docker like a pro. From installation checks to multi-service orchestration using Docker Compose.

---

## 📚 Table of Contents

- [🐳 Getting Started with Docker](#-getting-started-with-docker)
- [📁 Dockerizing a Project](#-dockerizing-a-project)
- [⚙️ Dockerfile Configuration Examples](#️-dockerfile-configuration-examples)
- [🔨 Build & Run Docker Containers](#-build--run-docker-containers)
- [📦 Image and Container Management](#-image-and-container-management)
- [📂 Docker Volumes & Bind Mounts](#-docker-volumes--bind-mounts)
- [🧱 Docker Compose (Multi-Service Apps)](#-docker-compose-multi-service-apps)
- [🔍 Inspecting and Debugging](#-inspecting-and-debugging)
- [🧽 Cleanup](#-cleanup)
- [📄 Useful Files You May Need](#-useful-files-you-may-need)

---

## 🐳 Getting Started with Docker

Verify Docker is installed and get basic system info:

```bash
docker --version        # Check installed Docker version
docker info             # See system-wide Docker details
```

---

## 📁 Dockerizing a Project

Make your app Docker-ready:

```bash
docker init             # Auto-creates a basic Dockerfile (in newer versions)
```

### Manually Create Required Files

- `Dockerfile`: Describes how to build and run your app.
- `.dockerignore`: Lists files/folders to exclude from Docker context.

Example `.dockerignore`:
```
.git
node_modules
*.log
target/
.env
```

---

## ⚙️ Dockerfile Configuration Examples

### Java Spring Boot

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Python Flask

```dockerfile
FROM python:3.11
WORKDIR /app
COPY . .
RUN pip install -r requirements.txt
CMD ["python", "app.py"]
```

---

## 🔨 Build & Run Docker Containers

### Build Docker image

```bash
docker build -t my-app .
```

### Run container and expose port

```bash
docker run -p 8080:8080 my-app
```

### Run in detached mode with a name

```bash
docker run -d --name my-app-container my-app
```

---

## 📦 Image and Container Management

| Command                            | Description                           |
|------------------------------------|---------------------------------------|
| `docker ps`                        | List running containers               |
| `docker ps -a`                     | List all containers                   |
| `docker stop <container>`         | Stop a running container              |
| `docker start <container>`        | Start a stopped container             |
| `docker rm <container>`           | Remove a container                    |
| `docker rmi <image>`              | Remove an image                       |
| `docker exec -it <container> bash`| Access terminal inside container      |

---

## 📂 Docker Volumes & Bind Mounts

### Create and use a named volume

```bash
docker volume create my-volume
docker run -v my-volume:/data my-app
```

### Bind mount local directory

```bash
docker run -v $(pwd):/app my-app
```

---

## 🧱 Docker Compose (Multi-Service Apps)

Use `docker-compose.yml` for multi-container setups:

```bash
docker-compose up             # Start services
docker-compose up --build     # Rebuild and start
docker-compose down           # Stop and remove
docker-compose ps             # View service status
```

---

## 🔍 Inspecting and Debugging

| Command                             | Description                            |
|-------------------------------------|----------------------------------------|
| `docker logs <container>`           | View container logs                    |
| `docker inspect <container|image>`  | Get detailed info                      |
| `docker network ls`                 | List Docker networks                   |
| `docker stats`                      | View live resource usage               |

---

## 🧽 Cleanup

Keep your Docker environment clean:

```bash
docker system prune        # Remove stopped containers, unused images, etc.
docker volume prune        # Remove unused volumes
docker image prune         # Remove dangling images
```

---

## 📄 Useful Files You May Need

| File                   | Purpose                                         |
|------------------------|-------------------------------------------------|
| `Dockerfile`           | Instructions to build your Docker image         |
| `.dockerignore`        | Files to exclude from build context             |
| `docker-compose.yml`   | Define and run multi-container applications     |
| `.env`                 | Store environment variables for Compose         |

---
