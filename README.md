# Eternal Bouquet 🌸

## Overview

Eternal Bouquet is a Spring Boot web application for browsing, ordering, and managing flower bouquets. The platform provides a modern online flower shop experience where customers can explore bouquets, add them to favorites, place orders, and leave reviews.

The application is developed as an individual project for the Spring Fundamentals course at SoftUni.

---

## Features

### Guest Users

* Browse all available bouquets
* View bouquet details
* Filter bouquets by category and size
* Register a new account
* Login to the system

### Registered Users

* Add bouquets to favorites
* Manage shopping cart
* Checkout and create orders
* View order history
* View order details
* Submit reviews for bouquets

### Administrator Features

- Edit existing bouquets
- Manage users
- Change user roles
- Access administrative functionality unavailable to regular users
---

## Technologies Used

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* MySQL

### Frontend

* Thymeleaf
* HTML5
* CSS3

### Additional Libraries

* Lombok
* Jakarta Validation

---

## Database Design

The application uses a relational MySQL database.

Main entities:

* User
* Bouquet
* Order
* OrderItem
* Review

Relationships:

* One User can create many Orders
* One User can write many Reviews
* One Order contains multiple OrderItems
* One Bouquet can have multiple Reviews
* Users can maintain a list of favorite bouquets

---

## Application Architecture

The project follows a layered architecture:

* Controllers
* Services
* Repositories
* DTOs
* Mappers
* Entities

This separation ensures maintainability, scalability, and clean code organization.

---

## Main Functionalities

### Authentication

* User registration
* User login
* Session-based authentication
* Role-based authorization

### Bouquet Management

* Browse bouquet catalog
* View bouquet details
* Edit bouquets
* Category and size filtering

### Shopping Cart

* Add bouquet to cart
* Remove bouquet from cart
* Checkout process

### Orders

* Create orders
* View order history
* View detailed order information

### Reviews

* Leave reviews and ratings
* Display customer feedback

### Favorites

* Add bouquet to favorites
* Remove bouquet from favorites
* View favorite bouquets

---

## Future Improvements

* Spring Security integration
* Email notifications
* Online payment integration
* Advanced search functionality
* Bouquet image uploads
* Admin dashboard analytics
* REST API support

---

## Installation

1. Clone the repository

```bash
git clone <repository-url>
```

2. Create MySQL database

```sql
CREATE DATABASE bouquets;
```

3. Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bouquets
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run the application

```bash
mvn spring-boot:run
```

5. Open in browser

```text
http://localhost:8080
```

---

## Author

Preslava Petrova

SoftUni Spring Fundamentals Project – 2026
