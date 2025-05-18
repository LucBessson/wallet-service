#  Wallet Service

Простое REST API приложение на Spring Boot для управления кошельками. Поддерживает создание кошельков и операции пополнения/списания средств.

---

##  Стек технологий

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Liquibase
- Docker

---

##  Быстрый старт (Docker)

### 1. Собери образ

```bash
docker build -t wallet-service .
```

### 2. Запусти PostgreSQL

```bash
docker run --name wallet-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15
```

### 3. Запусти приложение

```bash
docker run --name wallet-app -p 8080:8080   -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/postgres   -e SPRING_DATASOURCE_USERNAME=postgres   -e SPRING_DATASOURCE_PASSWORD=postgres   wallet-service
```

---

##  REST API

###  Пополнение/списание средств

`POST /api/wallets/{walletId}/operation`

```json
{
  "operationType": "DEPOSIT",
  "amount": 100.0
}
```

- `operationType`: `"DEPOSIT"` или `"WITHDRAW"`
- `amount`: сумма операции

###  Получение кошелька

`GET /api/wallets/{walletId}`

---

##  Тестирование

```bash
./mvnw test
```

---

##  Структура проекта

```
wallet-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/wallet/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── WalletServiceApplication.java
│   └── resources/
│       └── db/changelog/ (Liquibase миграции)
├── Dockerfile
├── pom.xml
└── README.md
```

---

##  Автор

Nikita © 2025
