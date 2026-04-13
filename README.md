# 📚 Spring Data JPA Books Project

A comprehensive **Spring Boot REST API** for managing books and authors with a MySQL database. This project demonstrates best practices for building scalable, production-grade Java applications using Spring Data JPA, validation, error handling, and API documentation.

---

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Error Handling](#error-handling)
- [File Upload](#file-upload)
- [Validation](#validation)
- [Contributing](#contributing)

---

## ✨ Features

- ✅ **RESTful API** for Books and Authors management
- ✅ **Spring Data JPA** for seamless database operations
- ✅ **MySQL Database Integration** with automatic schema creation
- ✅ **Input Validation** with custom validators and error messages
- ✅ **Comprehensive Error Handling** with global exception handler
- ✅ **File Upload Functionality** for storing book-related files
- ✅ **Swagger/OpenAPI Documentation** for easy API exploration
- ✅ **Scheduled Tasks** using ShedLock for distributed systems
- ✅ **DTO Pattern** for clean separation of concerns
- ✅ **Lombok** for reducing boilerplate code
- ✅ **IP Address Validation** for request filtering

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Programming language |
| **Spring Boot** | 2.6.1 | Framework & HTTP server |
| **Spring Data JPA** | 2.6.1 | ORM & database access |
| **MySQL** | Latest | Relational database |
| **Lombok** | 1.18.22 | Boilerplate reduction |
| **Swagger/OpenAPI** | 1.6.4 | API documentation |
| **ShedLock** | 4.27.0 | Scheduled task coordination |
| **Maven** | 3.6+ | Build & dependency management |

---

## 📦 Prerequisites

Before running this project, ensure you have:

- **Java 17** or higher installed
- **Maven 3.6+** installed
- **MySQL 8.0+** server running
- **Git** for cloning the repository

---

## 🚀 Installation

### Step 1: Clone the Repository

git clone https://github.com/Sasatayea/data-jpa-books-project.git
cd data-jpa-books-project/data-jpa-books-project

### Step 2: Create MySQL Database

CREATE DATABASE books_db;
CREATE USER 'bookuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON books_db.* TO 'bookuser'@'localhost';
FLUSH PRIVILEGES;

### Step 3: Update Configuration

Edit `src/main/resources/application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/books_db
spring.datasource.username=bookuser
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

server.port=8080

### Step 4: Build the Project

mvn clean install

---

## ⚙️ Configuration

### Application Properties

The application requires the following configuration:

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/books_db
spring.datasource.username=root
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# Server Configuration
server.port=8080
server.servlet.context-path=/api

# File Upload
file.upload.dir=/uploads/books

---

## 📁 Project Structure

data-jpa-books-project/
├── src/
│   ├── main/
│   │   ├── java/com/global/book/
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── BookController.java
│   │   │   │   ├── AutherController.java
│   │   │   │   └── FileUploadController.java
│   │   │   ├── entity/              # JPA Entities & DTOs
│   │   │   │   ├── Book.java
│   │   │   │   ├── Auther.java
│   │   │   │   ├── BookDto.java
│   │   │   │   └── AutherSearch.java
│   │   │   ├── repository/          # Spring Data Repositories
│   │   │   ├── service/             # Business Logic
│   │   │   ├── config/              # Configuration Classes
│   │   │   │   ├── WebConfig.java
│   │   │   │   ├── OpenApiConfig.java
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   └── SchedulerConfig.java
│   │   │   ├── validator/           # Custom Validators
│   │   │   │   ├── IpAdress.java
│   │   │   │   └── IpAdressImpl.java
│   │   │   ├── error/               # Exception Handling
│   │   │   │   ├── GlobalExecptionHandler.java
│   │   │   │   ├── RecoredNotFoundExecption.java
│   │   │   │   ├── DaplicateRecoredException.java
│   │   │   │   ├── FileStorageException.java
│   │   │   │   └── ErrorResponse.java
│   │   │   └── DataJpaBooksProjectApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Unit Tests
└── pom.xml                          # Maven Configuration

---

## 🔌 API Endpoints

### Books API

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/books` | Get all books |
| `GET` | `/api/books/{id}` | Get book by ID |
| `POST` | `/api/books` | Create a new book |
| `PUT` | `/api/books/{id}` | Update a book |
| `DELETE` | `/api/books/{id}` | Delete a book |
| `GET` | `/api/books/search` | Search books by criteria |

### Authors API

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/authors` | Get all authors |
| `GET` | `/api/authors/{id}` | Get author by ID |
| `POST` | `/api/authors` | Create a new author |
| `PUT` | `/api/authors/{id}` | Update an author |
| `DELETE` | `/api/authors/{id}` | Delete an author |
| `GET` | `/api/authors/search` | Search authors |

### File Upload API

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/files/upload` | Upload a file |
| `GET` | `/api/files/{fileId}` | Download a file |

---

## 🗄️ Database Schema

### Books Table

CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    author_id BIGINT NOT NULL,
    published_date DATE,
    pages INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

### Authors Table

CREATE TABLE authors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    country VARCHAR(100),
    birth_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

---

## ▶️ Running the Application

### Using Maven

mvn spring-boot:run

Or build a JAR and run it:
mvn clean package
java -jar target/data-jpa-books-project-0.0.1-SNAPSHOT.jar

### Using IDE

1. Import the project into your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Right-click on `DataJpaBooksProjectApplication.java`
3. Select "Run" or "Debug"

The application will start on http://localhost:8080

---

## 📖 API Documentation

Once the application is running, access the Swagger UI at:

http://localhost:8080/swagger-ui.html

Or the OpenAPI JSON specification at:

http://localhost:8080/v3/api-docs

### Example Request - Create a Book

curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java",
    "isbn": "978-0-13-468599-1",
    "authorId": 1,
    "publishedDate": "2018-01-01",
    "pages": 416
  }'

### Example Response

{
  "id": 1,
  "title": "Effective Java",
  "isbn": "978-0-13-468599-1",
  "author": {
    "id": 1,
    "name": "Joshua Bloch"
  },
  "publishedDate": "2018-01-01",
  "pages": 416,
  "createdAt": "2024-01-15T10:30:00Z"
}

---

## ⚠️ Error Handling

The application implements comprehensive error handling with standardized error responses:

### Error Response Format

{
  "timestamp": "2024-01-15T10:30:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Book with ID 999 not found",
  "path": "/api/books/999"
}

### Common Exceptions

| Exception | HTTP Status | Scenario |
|-----------|-------------|----------|
| `RecordNotFoundExecution` | 404 | Resource not found |
| `DuplicateRecordedException` | 409 | Duplicate entry |
| `FileStorageException` | 400 | File upload error |
| `ValidationException` | 400 | Invalid input |

---

## 📤 File Upload

The application supports file uploads with validation:

### Upload Endpoint

curl -X POST http://localhost:8080/api/files/upload \
  -F "file=@/path/to/file.pdf"

### Supported Features

- ✅ Multiple file format support
- ✅ File size validation
- ✅ Virus scanning (optional)
- ✅ Secure storage with unique file IDs

---

## ✔️ Validation

The project includes custom validators:

### IP Address Validation

@IpAdress
private String ipAddress;

### Built-in Validations

@NotNull(message = "Title cannot be null")
@NotBlank(message = "Title cannot be blank")
private String title;

@Email(message = "Invalid email format")
private String email;

@Min(value = 1, message = "Pages must be at least 1")
private Integer pages;

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (git checkout -b feature/amazing-feature)
3. **Commit** your changes (git commit -m 'Add amazing feature')
4. **Push** to the branch (git push origin feature/amazing-feature)
5. **Open** a Pull Request

### Coding Standards

- Use **Lombok** for getters/setters
- Follow **REST** conventions
- Write **unit tests** for new features
- Update **Swagger documentation**
- Keep **classes single-responsibility**

---

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 📧 Contact & Support

For questions or support, please:

- Open an **Issue** on GitHub
- Contact the maintainers
- Check existing documentation

---

## 🎯 Roadmap

- [ ] Implement pagination for list endpoints
- [ ] Add caching layer (Redis)
- [ ] Implement JWT authentication
- [ ] Add rate limiting
- [ ] Create frontend UI
- [ ] Deploy to cloud (AWS/Azure/GCP)
- [ ] Add integration tests
- [ ] Performance optimization

---

**Happy coding! 🚀**
