# Film Rental Backend

A RESTful backend application for a **Film Rental Management System**, built with **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

The application provides APIs for retrieving information about films, actors, customers, rentals, inventory, and stores. It follows a layered backend architecture and uses DTOs, Mappers, JPA repositories, and unit testing with JUnit and Mockito.

---

## 🚀 Features

The backend provides REST APIs for:

* 🎬 Retrieving films by ID
* 🎭 Retrieving films by category
* 👤 Retrieving films by actor
* 🔥 Retrieving top-rented films
* 🎭 Retrieving all films associated with an actor
* 👥 Retrieving customer details
* 📋 Retrieving a customer's rental history
* 💳 Retrieving a customer's payment history
* 📦 Retrieving rentals by customer
* 🏪 Retrieving inventory details
* 🏬 Retrieving store details
* 📦 Retrieving inventory belonging to a store

The APIs are organized using versioned endpoints under:

```text
/api/v1
```

---

## 🛠️ Tech Stack

| Technology          | Purpose                                         |
| ------------------- | ----------------------------------------------- |
| **Java**            | Backend programming language                    |
| **Spring Boot**     | Application framework                           |
| **Spring Data JPA** | Database access and repository abstraction      |
| **Hibernate**       | ORM for mapping Java objects to database tables |
| **MySQL**           | Relational database                             |
| **Maven**           | Dependency management and build tool            |
| **JUnit 5**         | Unit testing                                    |
| **Mockito**         | Mocking dependencies during testing             |
| **REST API**        | Communication between clients and backend       |

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
                Client
                  │
                  ▼
            ┌─────────────┐
            │ Controller  │
            └──────┬──────┘
                   │
                   ▼
            ┌─────────────┐
            │   Service   │
            └──────┬──────┘
                   │
                   ▼
            ┌─────────────┐
            │ Repository  │
            └──────┬──────┘
                   │
                   ▼
            ┌─────────────┐
            │   MySQL DB  │
            └─────────────┘
```

### Controller Layer

Handles HTTP requests and exposes REST endpoints.

### Service Layer

Contains the application's business logic and coordinates operations between controllers and repositories.

### Repository Layer

Uses Spring Data JPA to interact with the database.

### Entity Layer

Contains JPA entities representing database tables and their relationships.

### DTO Layer

DTOs are used to control the data exposed through the REST APIs rather than directly returning database entities.

### Mapper Layer

Mapper classes convert between entities and DTOs.

For example:

```text
Customer Entity
      ↓
CustomerMapper
      ↓
CustomerResponse
```

---

## 📡 API Endpoints

### 🎬 Film APIs

| Method | Endpoint                              | Description                               |
| ------ | ------------------------------------- | ----------------------------------------- |
| `GET`  | `/api/v1/films/{id}`                  | Retrieve a film by ID                     |
| `GET`  | `/api/v1/films/category/{categoryId}` | Retrieve films belonging to a category    |
| `GET`  | `/api/v1/films/actor/{actorId}`       | Retrieve films associated with an actor   |
| `GET`  | `/api/v1/films/top-rented`            | Retrieve the most frequently rented films |

---

### 🎭 Actor APIs

| Method | Endpoint                    | Description                                 |
| ------ | --------------------------- | ------------------------------------------- |
| `GET`  | `/api/v1/actors/{id}/films` | Retrieve all films associated with an actor |

---

### 👥 Customer APIs

| Method | Endpoint                          | Description                           |
| ------ | --------------------------------- | ------------------------------------- |
| `GET`  | `/api/v1/customers/{id}`          | Retrieve customer details             |
| `GET`  | `/api/v1/customers/{id}/rentals`  | Retrieve a customer's rental history  |
| `GET`  | `/api/v1/customers/{id}/payments` | Retrieve a customer's payment history |

---

### 📋 Rental APIs

| Method | Endpoint                                | Description                                 |
| ------ | --------------------------------------- | ------------------------------------------- |
| `GET`  | `/api/v1/rentals/customer/{customerId}` | Retrieve rentals associated with a customer |

---

### 📦 Inventory APIs

| Method | Endpoint                 | Description                      |
| ------ | ------------------------ | -------------------------------- |
| `GET`  | `/api/v1/inventory/{id}` | Retrieve inventory details by ID |

---

### 🏪 Store APIs

| Method | Endpoint                        | Description                             |
| ------ | ------------------------------- | --------------------------------------- |
| `GET`  | `/api/v1/stores/{id}`           | Retrieve store details                  |
| `GET`  | `/api/v1/stores/{id}/inventory` | Retrieve inventory belonging to a store |

---

## 🗄️ Database

The application uses **MySQL** and was developed using the **Sakila sample database**, a relational database designed around a DVD rental business.

The database contains entities such as:

```text
Customer
    │
    ├── Rental
    │      │
    │      └── Inventory
    │              │
    │              └── Film
    │
    └── Payment

Film
 ├── Actor
 └── Category

Store
 └── Inventory
```

JPA and Hibernate are used to map these database relationships to Java entities.

---

## 📁 Project Structure

The backend follows a separation-of-concerns approach:

```text
src/
├── main/
│   ├── java/
│   │   └── ...
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── entity/
│   │       ├── dto/
│   │       └── mapper/
│   │
│   └── resources/
│       └── application.yml
│
└── test/
    └── java/
        └── ...
```

The major responsibilities are:

```text
Controller → Handles API requests

Service → Contains business logic

Repository → Communicates with database

Entity → Represents database tables

DTO → Represents API request/response data

Mapper → Converts between Entity and DTO
```

---

## 🧪 Testing

Unit testing is implemented using **JUnit and Mockito**.

The Customer service functionality includes tests for:

* Retrieving a customer by ID
* Retrieving customer rentals
* Retrieving customer payments
* Successful service operations
* Relevant failure/exception scenarios

The project also includes tests for film retrieval functionality.

Mockito is used to mock repository dependencies so that service-layer logic can be tested independently of the database.

Example:

```text
CustomerServiceTest
├── getCustomerById
├── getCustomerRentals
├── getCustomerPayments
└── additional service scenarios
```

---

## 👨‍💻 Contribution

This was developed as a **team project**, with different team members working on different parts of the backend.

### My Contribution — Customer Module

My primary contribution was the **Customer module**, including:

* Implemented Customer REST endpoints
* Implemented `CustomerService` and `CustomerServiceImpl`
* Worked with `CustomerRepository`
* Added `CustomerResponse` DTO
* Added `CreateCustomerRequest` DTO
* Implemented `CustomerMapper`
* Implemented customer rental retrieval
* Implemented customer payment retrieval
* Added the `/api/v1/customers` API structure
* Added unit tests for Customer service functionality using JUnit and Mockito
* Added tests covering customer retrieval, rental retrieval, and payment retrieval

### Team Contributions

The complete backend also contains functionality for:

* Film retrieval
* Actor-film relationships
* Rental retrieval
* Inventory retrieval
* Store and store-inventory retrieval
* Top-rented film retrieval

These modules were developed collaboratively by the team.

---

## ⚙️ Setup & Installation

### Prerequisites

Make sure you have the following installed:

* Java 17+
* Maven
* MySQL
* Git

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

Then navigate to the project:

```bash
cd film-rental
```

### 2. Configure the database

Create/configure the MySQL database and make sure the required Sakila database is available.

Update the database configuration in:

```text
src/main/resources/application.yml
```

Example:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sakila
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
```

Replace the credentials with your local MySQL credentials.

> Never commit real database credentials to the repository.

### 3. Build the application

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

If the application is configured to run on port `8081`:

```text
http://localhost:8081
```

---

## 🔍 Testing the APIs

The APIs can be tested using tools such as:

* Postman
* IntelliJ HTTP Client
* cURL
* Swagger/OpenAPI, if enabled in the current configuration

Example:

```http
GET http://localhost:8081/api/v1/customers/1
```

Example customer rental request:

```http
GET http://localhost:8081/api/v1/customers/1/rentals
```

Example customer payment request:

```http
GET http://localhost:8081/api/v1/customers/1/payments
```

---

## 📚 Concepts Demonstrated

This project demonstrates practical backend development concepts including:

* Spring Boot
* REST API development
* Layered architecture
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* Entity relationships
* Repository pattern
* DTO pattern
* Entity-to-DTO mapping
* Service-layer business logic
* API versioning
* MySQL database integration
* Unit testing
* JUnit
* Mockito
* Git and GitHub collaboration

---

## 🔮 Future Improvements

Possible future improvements include:

* Global exception handling using `@ControllerAdvice`
* Request validation using Bean Validation
* Pagination and sorting
* More comprehensive integration testing
* Improved API error responses
* API documentation using OpenAPI/Swagger
* Docker support
* CI/CD pipeline using GitHub Actions
* Logging and monitoring
* Authentication and authorization where required

---

## 👤 Author

**Gungun Singh**

B.Tech — Computer Science & Engineering

GitHub: https://github.com/Gungun-Singh
