\# Evaluación 04 - Arquitectura de Microservicios



\## Descripción



Este proyecto corresponde al desarrollo de una solución basada en Arquitectura de Microservicios utilizando Spring Boot y Spring Cloud.



El sistema implementa una arquitectura distribuida para la gestión de usuarios, proyectos y tareas, aplicando principios de desacoplamiento, configuración centralizada, descubrimiento de servicios y comunicación entre microservicios mediante OpenFeign.



Además, se implementan mecanismos de tolerancia a fallos utilizando Resilience4j, permitiendo que el sistema continúe funcionando cuando alguno de los servicios no se encuentre disponible.



\---



\# Objetivos



\- Implementar una arquitectura basada en microservicios.

\- Centralizar la configuración mediante Spring Cloud Config Server.

\- Registrar y descubrir servicios utilizando Netflix Eureka.

\- Exponer los servicios mediante Spring Cloud Gateway.

\- Implementar comunicación entre microservicios utilizando OpenFeign.

\- Aplicar Circuit Breaker y Retry con Resilience4j.

\- Gestionar información mediante bases de datos MySQL independientes para cada servicio.

\- Contenerizar la solución mediante Docker y Docker Compose.



\---



\# Arquitectura del Proyecto



El sistema está compuesto por los siguientes componentes:



\- Config Server

\- Eureka Server

\- API Gateway

\- Usuario Service

\- Proyecto Service

\- Tarea Service

\- Config Repository



La comunicación entre servicios se realiza mediante OpenFeign y el descubrimiento dinámico de servicios a través de Eureka.



\---



\# Tecnologías Utilizadas



\- Java 21

\- Spring Boot 3.5.16

\- Spring Cloud 2025.0.3

\- Spring Cloud Config Server

\- Netflix Eureka

\- Spring Cloud Gateway

\- Spring Data JPA

\- OpenFeign

\- Resilience4j

\- MySQL

\- Maven

\- Docker

\- Docker Compose

\- Postman

\- Git y GitHub



\---



\# Estructura del Proyecto



```text

evaluacion04-microservicios

│

├── api-gateway

├── config-repo

├── config-server

├── eureka-server

├── proyecto-service

├── tarea-service

├── usuario-service

│

├── docker-compose.yml

├── script.sql

└── README.md

```



\---



\# Microservicios



\## Usuario Service



Permite realizar el mantenimiento completo de usuarios mediante operaciones CRUD.



Funciones principales:



\- Registrar usuarios

\- Consultar usuarios

\- Actualizar usuarios

\- Eliminar usuarios



\---



\## Proyecto Service



Administra los proyectos registrados y consulta información del usuario responsable mediante OpenFeign.



Funciones principales:



\- CRUD de proyectos

\- Consulta del responsable del proyecto

\- Circuit Breaker y Retry



\---



\## Tarea Service



Administra las tareas asociadas a los proyectos y consulta información del proyecto correspondiente.



Funciones principales:



\- CRUD de tareas

\- Consulta del proyecto asociado

\- Circuit Breaker y Retry



\---



\# Configuración Centralizada



Toda la configuración de los microservicios se administra mediante Spring Cloud Config Server utilizando un repositorio de configuración (`config-repo`).



Esto permite centralizar parámetros como:



\- Puertos

\- Configuración de MySQL

\- Configuración de Eureka

\- Mensajes del sistema



\---



\# Descubrimiento de Servicios



Netflix Eureka permite registrar automáticamente todos los microservicios para facilitar la comunicación entre ellos sin depender de direcciones IP fijas.



\---



\# Comunicación entre Microservicios



Se implementa comunicación mediante OpenFeign.



Flujo de llamadas:



Proyecto Service → Usuario Service



Tarea Service → Proyecto Service



\---



\# Resiliencia



Se implementaron mecanismos de tolerancia a fallos utilizando Resilience4j.



Características implementadas:



\- Circuit Breaker

\- Retry

\- Fallback personalizado



Cuando un servicio deja de estar disponible, el sistema responde con información alternativa sin interrumpir el funcionamiento general.



\---



\# Docker



El proyecto incluye:



\- Dockerfile para cada microservicio.

\- Archivo docker-compose.yml para el despliegue de toda la arquitectura.



\---



\# Base de Datos



Cada microservicio utiliza una base de datos independiente:



\- usuario\_db

\- proyecto\_db

\- tarea\_db



El archivo `script.sql` permite crear las bases de datos necesarias.



\---



\# Colección Postman



Se incluye una colección de Postman con las pruebas realizadas sobre los diferentes microservicios y el API Gateway.



\---



\# Autor



\*\*Naomi Isabel Sanchez Chavarria\*\*



Carrera de Diseño y Desarrollo de Software



TECSUP

