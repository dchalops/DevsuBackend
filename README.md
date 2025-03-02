# Devsu Spring Boot Microservices

Este proyecto es un conjunto de microservicios desarrollados con **Spring Boot**, **Spring Cloud** y **Docker**, que gestionan cuentas y transacciones bancarias, autenticación y mensajería con RabbitMQ.

## 📌 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 2.7.14**
- **Spring Cloud (Eureka, Config Server, Gateway)**
- **Spring Data JPA (PostgreSQL)**
- **RabbitMQ** (Mensajería)
- **Docker y Docker Compose**
- **Maven** (Gestor de dependencias)
- **Swagger/OpenAPI** (Documentación de API)
- **JWT (Json Web Token)** (Autenticación)
- **Lombok** (Para simplificar el código)

---

## 🚀 Instalación y Configuración

### **1️⃣ Clonar el repositorio**
```bash
https://github.com/dchalops/devsu.git
cd devsu-spring-boot-microservices
```

### **2️⃣ Configurar Variables de Entorno**
Antes de ejecutar los servicios, asegúrate de configurar los archivos de propiedades (`.env`, `application.yml`, `application.properties`) con las credenciales de base de datos, eureka y rabbitmq.

Ejemplo de `.env`:
```env
POSTGRES_USER=postgres
POSTGRES_PASSWORD=55
EUREKA_URI=http://192.168.1.4:8761/eureka
CONFIG_SERVER=http://192.168.1.4:8888
```

### **3️⃣ Levantar Servicios con Docker**
Para iniciar las bases de datos y los servicios en contenedores:
```bash
docker-compose up --build
```

Si deseas correr solo PostgreSQL y RabbitMQ sin los microservicios:
```bash
docker-compose up postgres_clients postgres_accounts rabbitmq
```

Para detener los contenedores:
```bash
docker-compose down
```

### **4️⃣ Ejecutar los Microservicios**
Cada microservicio puede ejecutarse de manera individual:

#### **🏦 `clients-persons-service`**
```bash
cd clients-persons-service
mvn clean spring-boot:run
```

#### **💰 `accounts-movements-service`**
```bash
cd accounts-movements-service
mvn clean spring-boot:run
```

#### **🌍 `eureka-server` (Discovery)**
```bash
cd eureka-server
mvn clean spring-boot:run
```

#### **🛠️ `config-server`**
```bash
cd config-server
mvn clean spring-boot:run
```

#### **🚪 `api-gateway`**
```bash
cd api-gateway
mvn clean spring-boot:run
```

---

## 📖 Endpoints Principales

### **Clientes y Personas**
- **GET** `/v1/clients/{clientId}` → Obtener cliente por ID
- **POST** `/v1/clients` → Crear nuevo cliente

### **Cuentas y Movimientos**
- **GET** `/v1/accounts/{accountId}` → Obtener cuenta por ID
- **POST** `/v1/accounts` → Crear nueva cuenta
- **POST** `/v1/transactions` → Registrar transacción

### **Autenticación**
- **POST** `/auth/login` → Generar Token JWT
- **POST** `/auth/register` → Registrar usuario

### **Reportes**
- **GET** `/v1/transactions/reports?date_start=2025-02-08&date_end=2025-02-20&clientId=123456` → Generar reporte de transacciones

---

## 🔗 Documentación con Swagger
Una vez que los microservicios están corriendo, accede a la documentación de la API:
```
http://localhost:8080/swagger-ui.html
```

---

## 🛠 Troubleshooting

1️⃣ **Error con PostgreSQL "database does not exist"**  
Solución: Crea la base de datos manualmente en PostgreSQL.
```sql
CREATE DATABASE clientsMicroservice;
CREATE DATABASE accountsMicroservice;
```

2️⃣ **Error con RabbitMQ "NOT_FOUND - no queue 'transaction.queue'"**  
Solución: Asegúrate de que la cola esté creada en el RabbitMQ Management UI (`http://localhost:15672`).

3️⃣ **No conecta a Eureka**  
Solución: Verifica que `EUREKA_URI` sea correcto y que el servicio esté en ejecución.

---

## 📌 Contribuciones
1. **Forkea el repositorio**
2. **Crea una nueva rama (`feature-nueva-funcionalidad`)**
3. **Haz un commit con tus cambios**
4. **Haz un push a tu rama y crea un Pull Request**
