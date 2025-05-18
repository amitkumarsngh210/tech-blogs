# Tech Blogs API

A Spring Boot-based REST API for managing tech blogs. This application provides endpoints to create, read, update, and delete tech blog posts.

## Prerequisites

- Java 17 or higher
- Maven
- MySQL 8.0 or higher

## Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/tech-blogs.git
cd tech-blogs
```

2. Configure MySQL:
   - Create a MySQL database named `tech_blogs`
   - Update the database credentials in `src/main/resources/application.properties` if needed

3. Build the application:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Create a Blog
```http
POST /api/blogs
Content-Type: application/json

{
    "title": "Getting Started with Spring Boot",
    "content": "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications...",
    "author": "John Doe",
    "tags": ["Spring Boot", "Java", "Backend"]
}
```

### Get All Blogs
```http
GET /api/blogs
```

### Get Blog by ID
```http
GET /api/blogs/{id}
```

### Get Blogs by Topic
```http
GET /api/blogs/topic/{topic}
```

### Get Blogs by Author
```http
GET /api/blogs/author/{author}
```

### Update Blog
```http
PUT /api/blogs/{id}
Content-Type: application/json

{
    "title": "Updated Title",
    "content": "Updated content...",
    "author": "John Doe",
    "tags": ["Spring Boot", "Java", "Backend"]
}
```

### Delete Blog
```http
DELETE /api/blogs/{id}
```

## Features

- RESTful API design
- MySQL database integration
- Input validation
- Global exception handling
- Transactional management
- Lombok for reducing boilerplate code
- OpenTelemetry integration for distributed tracing
- Micrometer metrics for monitoring
- Prometheus metrics export
- Structured logging with Logback

## Technologies Used

- Spring Boot 3.2.3
- Spring Data JPA
- MySQL
- Lombok
- Maven
- Java 17
- OpenTelemetry
- Micrometer
- Prometheus
- Logback
