# ProjectNest — Project and Task Management System

ProjectNest is a microservices application for managing projects, tasks, and notifications.
The system is built on Spring Boot and uses Spring Cloud for service communication, as well as Kafka for event streaming.

## 🛠️ Technologies

- ☕ **Java 17**
- 🚀 **Spring Boot 3.5.5**
- 🚀 **Spring Cloud**
- 🗄️ **Spring Data JPA / Hibernate**
- 🔐 **Spring Security + JWT**
- 📡 **Spring Kafka**
- 🐘 **PostgreSQL**
- 🐳 **Docker + Docker Compose**
- 📨 **Kafka** 

## 🐳 How to Run the Project

1. Clone the repository:
```yaml
git clone https://github.com/helloegor03/ProjectNest.git
cd ProjectNest
```
2. Update the YAML configuration files with your database settings.

3. Start all containers:
docker compose up --build

4. After successful startup, the services will be available at:
```yaml
Gateway:             http://localhost:8222
Auth Service:        http://localhost:8090
Project Service:     http://localhost:8070
Task Service:        http://localhost:8050
Notification Service:http://localhost:8060
Config Server:       http://localhost:8888
```

## 🔐 Security

- Authentication is implemented using JWT.
- Users receive a token via /auth/login, which should be included in the Authorization: Bearer <token> header when accessing other services.

## 📡 Inter-Service Communication

- **Gateway routes all requests based on prefixes.**
  
- **Auth Service validates JWT and manages users.**
  
- **Project Service and Task Service publish events to Kafka when entities are created, updated, or deleted.**
  
- **Notification Service subscribes to Kafka topics and sends notifications (Gmail API).**


## TODO:

- Complete the notification system
- Implement statistics for tasks and projects
- Add frontend
