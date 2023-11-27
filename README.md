# JWT Authentication with Spring Boot and Kotlin

This project showcases a JWT-based authentication system implemented using Spring Boot, Spring Security, Spring Data MongoDB, Docker Compose, and Kotlin. It provides role-based authentication for secure access to endpoints.


## Technologies Used

- **Spring Boot**: Backend framework for building robust Java applications.
- **Spring Security**: Provides authentication and authorization functionalities.
- **Spring Data MongoDB**: Integrates MongoDB database for data persistence.
- **Kotlin**: Programming language for concise and expressive code.
- **Docker Compose**: Used for containerization and running multiple services.

## Features

- **User Registration**: `/api/v1/register`
    - Allows new users to register by providing necessary details like firstname, lastname, email, password, etc.

- **User Login**: `/api/v1/login`
    - Enables registered users to authenticate and obtain a JWT token for subsequent access to secured endpoints.

- **Secured Endpoint**: `/api/v1/user`
    - Accessible only with a valid JWT token.
    - Provides user-related information or actions, accessible only to authenticated users.

