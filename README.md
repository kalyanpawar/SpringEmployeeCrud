# SpringEmployeeCrud

This is a Spring Boot application for managing employees with full CRUD (Create, Read, Update, Delete) operations.

## Features
- RESTful API for employee management
- Basic Authentication for all /api/* endpoints
- Swagger UI for API documentation and testing
- In-memory authentication (username: `admin`, password: `admin123`)

## Authentication
All API endpoints under `/api/` are protected with HTTP Basic Authentication. You must provide the following credentials:
- **Username:** admin
- **Password:** admin123

When using Swagger UI, click the **Authorize** button and enter the credentials above to access protected endpoints.

## Running the Application
1. Start the application using `mvnw spring-boot:run` or your IDE.
2. Access Swagger UI at `http://localhost:8080/swagger-ui.html` or `http://localhost:8080/swagger-ui/index.html`.
3. Authorize and test the endpoints.

## API Testing with Bruno

Bruno is an open-source API client for testing REST APIs. This project includes a Bruno collection for testing all employee endpoints.

### Download Bruno
- Visit [https://www.usebruno.com/downloads](https://www.usebruno.com/downloads) and download Bruno for your operating system.
- Install Bruno by following the instructions on the website.

### Open the Bruno Collection
- After installing Bruno, open the Bruno app.
- Click **Open Collection** and navigate to the [`Bruno`](bruno) folder in this project repository.
- Select the [`Employee_Crud`](bruno/Employee_Crud) folder to load all API requests for employee CRUD operations.
- Use the provided requests to test endpoints. Make sure to use the authentication credentials:
  - **Username:** admin
  - **Password:** admin123
