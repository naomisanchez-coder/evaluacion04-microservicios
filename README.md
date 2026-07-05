# 🚀 Evaluación 04 - Arquitectura de Microservicios

> Sistema distribuido desarrollado con **Spring Boot** y **Spring Cloud** para la gestión de usuarios, proyectos y tareas, implementando una arquitectura basada en microservicios.

---

# 📖 Descripción

Este proyecto implementa una arquitectura de microservicios aplicando los principales componentes del ecosistema Spring Cloud.

La solución integra configuración centralizada, descubrimiento de servicios, comunicación entre microservicios mediante OpenFeign, tolerancia a fallos utilizando Resilience4j y acceso unificado a través de un API Gateway.

---

# 🎯 Objetivos

- ✅ Implementar una arquitectura basada en microservicios.
- ✅ Centralizar la configuración mediante Config Server.
- ✅ Registrar servicios con Eureka.
- ✅ Exponer los servicios mediante API Gateway.
- ✅ Implementar comunicación entre microservicios usando OpenFeign.
- ✅ Aplicar Circuit Breaker y Retry.
- ✅ Implementar Fallback para tolerancia a fallos.
- ✅ Gestionar información mediante MySQL.
- ✅ Contenerizar la solución con Docker.

---

# 🏗️ Arquitectura

```text
                     +----------------------+
                     |     Config Server    |
                     +----------+-----------+
                                |
                                |
                     +----------v-----------+
                     |     Eureka Server    |
                     +----------+-----------+
                                |
             ---------------------------------------------
             |                    |                     |
             |                    |                     |
   +---------v-----+   +----------v------+   +----------v------+
   | UsuarioService|   | ProyectoService |   |  TareaService   |
   +---------------+   +-----------------+   +-----------------+
            ^                   ^                     |
            |                   |                     |
            +---------Feign-----+---------Feign-------+
                                |
                     +----------v-----------+
                     |     API Gateway      |
                     +----------------------+
```

---

# 🧩 Microservicios

## 👤 Usuario Service

Permite administrar la información de los usuarios.

### Funciones

- Registrar usuarios
- Consultar usuarios
- Actualizar usuarios
- Eliminar usuarios

---

## 📁 Proyecto Service

Gestiona los proyectos registrados.

### Funciones

- CRUD de proyectos
- Consulta del responsable mediante OpenFeign
- Circuit Breaker
- Retry

---

## ✅ Tarea Service

Gestiona las tareas asociadas a los proyectos.

### Funciones

- CRUD de tareas
- Consulta de proyectos mediante OpenFeign
- Circuit Breaker
- Retry

---

# 🔗 Comunicación entre Servicios

```text
Proyecto Service
        │
        ▼
 Usuario Service

Tarea Service
        │
        ▼
 Proyecto Service
```

La comunicación se implementa mediante **Spring Cloud OpenFeign**, permitiendo el consumo transparente entre microservicios.

---

# 🛡️ Resilience4j

Se implementaron mecanismos de tolerancia a fallos mediante:

- 🔄 Retry
- 🚦 Circuit Breaker
- 🛟 Fallback

Cuando un servicio no se encuentra disponible, el sistema responde con información alternativa sin afectar la disponibilidad general.

---

# ⚙️ Tecnologías

| Tecnología | Versión |
|------------|---------|
| ☕ Java | 21 |
| 🌱 Spring Boot | 3.5.16 |
| ☁️ Spring Cloud | 2025.0.3 |
| 🗄️ MySQL | 8 |
| 📦 Maven | 3.x |
| 🐳 Docker | Compose |
| 🧪 Postman | API Testing |
| 📝 Git | Control de versiones |
| 🌐 GitHub | Repositorio |

---

# 📂 Estructura del Proyecto

```text
evaluacion04-microservicios
│
├── 📁 api-gateway
├── 📁 config-repo
├── 📁 config-server
├── 📁 eureka-server
├── 📁 usuario-service
├── 📁 proyecto-service
├── 📁 tarea-service
│
├── 🐳 docker-compose.yml
├── 🗄️ script.sql
├── 📬 Evaluacion04-Microservicios.postman_collection.json
└── 📖 README.md
```

---

# 🗄️ Base de Datos

Cada microservicio trabaja con una base de datos independiente.

| Servicio | Base de Datos |
|----------|---------------|
| 👤 Usuario Service | usuario_db |
| 📁 Proyecto Service | proyecto_db |
| ✅ Tarea Service | tarea_db |

---

# 🐳 Docker

El proyecto incluye:

- ✅ Dockerfile para cada microservicio.
- ✅ docker-compose.yml para el despliegue completo.

---

# 📬 Colección Postman

Se incluye una colección con las pruebas realizadas para:

- Usuario Service
- Proyecto Service
- Tarea Service
- API Gateway
- Circuit Breaker
- Fallback

---

# 👩‍💻 Autor

**Naomi Isabel Sanchez Chavarria**

🎓 Carrera de Diseño y Desarrollo de Software

🏫 TECSUP

---

# ⭐ Observación

Este proyecto fue desarrollado con fines académicos para demostrar la implementación de una arquitectura moderna basada en microservicios utilizando el ecosistema Spring Cloud.

