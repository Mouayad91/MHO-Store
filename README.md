# MHO Games Store

## Overview

MHO Games Store is a web application built using Spring Boot and Thymeleaf.
 The application allows users to add, view, and update games in the inventory with basic validation and error handling.

## Features

- Add new games to the store inventory
- View all games in the inventory
- Update game details
- Basic validation and error handling for form inputs

## Technologies Used

- Java
- Spring Boot
- Thymeleaf
- Hibernate (JPA)
- JUnit 5
- Mockito

## Prerequisites

- Java 17 or later
- Maven

## Getting Started

### Application Setup

1. Clone the repository

    ```bash
    git clone https://github.com/Mouayad91/MHO-Store.git
    ```

2. Navigate to the project directory

    ```bash
    cd mho_games_store
    ```

3. Build the project using Maven

    ```bash
    mvn clean install
    ```

4. Run the application

    ```bash
    mvn spring-boot:run
    ```

### Endpoints

The application has the following endpoints:

#### Games

- `GET /` - View the form to add or update a game
- `POST /submit` - Submit a new game or update an existing one
- `GET /inventory` - View all games in the inventory

### Using the Application

1. **Open the application:**
    - URL: `http://localhost:8082`
    - Method: `GET`

2. **Add or Update a Game:**
    - Fill in the form with game details and submit.

3. **View Inventory:**
    - URL: `http://localhost:8082/inventory`
    - Method: `GET`
    - View all games in the inventory.

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Hibernate](https://hibernate.org/)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
