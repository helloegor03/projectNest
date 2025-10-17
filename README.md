# ProjectNest — система управления проектами и задачами

ProjectNest — это микросервисное приложение для управления проектами, задачами и уведомлениями.
Система построена на Spring Boot и использует Spring Cloud для взаимодействия между сервисами, а также Kafka для обмена событиями.

## 🛠️ Технологии

-Java 17
-Spring Boot 
-Spring Cloud 
-Spring Cloud Gateway
-Spring Cloud Config
-Spring Data JPA / Hibernate
-Spring Security + JWT
-Kafka / Zookeeper
-PostgreSQL
-Docker

## 🐳 Как запустить проект

1. Клонировать репозиторий:
```yaml
git clone https://github.com/helloegor03/ProjectNest.git
cd ProjectNest
```
2. Изменить yml файлы на свои настройки базы данных:

3. Запустить все контейнеры:
docker compose up --build

4. После успешного запуска сервисы будут доступны по адресам:
```yaml
Gateway:             http://localhost:8222
Auth Service:        http://localhost:8090
Project Service:     http://localhost:8070
Task Service:        http://localhost:8050
Notification Service:http://localhost:8060
Config Server:       http://localhost:8888
```

## 🔐 Безопасность

- Аутентификация реализована через JWT.
- Пользователь получает токен через /auth/login, после чего передаёт его в заголовке Authorization: Bearer <token> при обращении к другим сервисам.

## 📡 Взаимодействие между сервисами

-Gateway маршрутизирует все запросы по префиксам.
-Auth-Service проверяет JWT и управляет пользователями.
-Project-Service и Task-Service публикуют события в Kafka, когда создаются, обновляются или удаляются сущности.
-Notification-Service подписывается на Kafka-топики и рассылает уведомления (Gmail API).


## TODO:

- Дописать notification
-Реализовать статистику по задачам и проектам
-Подключить мониторинг через Prometheus + Grafana
-Добавить фронт
