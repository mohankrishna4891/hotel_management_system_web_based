# Hotel Management System

A Java Enterprise Web Application developed using **Servlets, JSP, Hibernate ORM, MySQL, and Maven** following the **MVC Architecture**. This project is a migration of a console-based Hotel Management System into a web-based application with role-based access control.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* User Login
* User Logout
* Session Management
* Authentication Filter
* Authorization Filter
* Role-Based Access Control

---

## 👨‍💼 Admin Module

### Room Management

* Add Room
* View Rooms
* Update Room
* Delete Room

### Reports

* Revenue Report Generation

---

## 👤 Customer Module

### Room Booking

* View Available Rooms
* Book Rooms
* View Booking History

### Billing

* Generate Bill
* View Bill Details
* Pay Bill

### Payment Methods

* UPI
* Card
* Cash

---

## 🏨 Receptionist Module

### Booking Management

* View All Bookings
* Check-In Customers
* Check-Out Customers

### Booking Status Flow

```text
PENDING → CONFIRMED → CHECKED_OUT
```

---

## 🧾 Billing Calculation

Bill amount is calculated based on the number of stay days.

```text
Base Amount = Room Price × Number of Stay Days

Final Amount = Base Amount + Tax − Discount
```

### Example

```text
Room Price = ₹2000/day

Stay Duration = 3 days

Base Amount = ₹6000

Tax (18%) = ₹1080

Discount (10%) = ₹600

Final Amount = ₹6480
```

---

## 🛠️ Technology Stack

### Backend

* Java 23
* Jakarta Servlet API
* JSP (Java Server Pages)
* Hibernate ORM
* Maven

### Database

* MySQL 8

### Server

* Apache Tomcat 10.1.55

### Architecture

* MVC (Model-View-Controller)
* DAO Layer
* Service Layer
* Repository Pattern

---

## 📂 Project Structure

```text
src/main/java

├── controller
├── service
│   └── impl
├── repository
│   └── impl
├── model
├── filter
├── exception
└── util

src/main/webapp

├── WEB-INF
│   └── views
│       ├── admin
│       ├── customer
│       ├── receptionist
│       ├── common
│       └── error
```

---

## 🗄️ Database Tables

### Users

| Column   | Description                     |
| -------- | ------------------------------- |
| user_id  | Primary Key                     |
| name     | User Name                       |
| email    | Login Email                     |
| password | Password                        |
| role     | ADMIN / CUSTOMER / RECEPTIONIST |

### Rooms

| Column      | Description          |
| ----------- | -------------------- |
| room_id     | Primary Key          |
| room_number | Room Number          |
| room_type   | Room Type            |
| price       | Per Day Charge       |
| status      | AVAILABLE / OCCUPIED |

### Bookings

| Column         | Description    |
| -------------- | -------------- |
| booking_id     | Primary Key    |
| customer_id    | Foreign Key    |
| room_id        | Foreign Key    |
| check_in       | Check-In Date  |
| check_out      | Check-Out Date |
| booking_status | Booking Status |

### Bills

| Column         | Description       |
| -------------- | ----------------- |
| bill_id        | Primary Key       |
| booking_id     | Foreign Key       |
| total_amount   | Total Bill Amount |
| tax            | Tax Amount        |
| discount       | Discount Amount   |
| payment_status | PENDING / PAID    |

---

## 🔒 Security

### Authentication Filter

Verifies whether a user is logged in before accessing protected resources.

### Authorization Filter

Restricts access based on user roles.

| Role         | Accessible URLs |
| ------------ | --------------- |
| ADMIN        | /admin/*        |
| CUSTOMER     | /customer/*     |
| RECEPTIONIST | /receptionist/* |

---

## ▶️ Application Workflow

### Admin

```text
Login
 ↓
Manage Rooms
 ↓
Generate Revenue Reports
```

### Customer

```text
Login
 ↓
View Available Rooms
 ↓
Book Room
 ↓
View Booking History
 ↓
Generate Bill
 ↓
Pay Bill
```

### Receptionist

```text
Login
 ↓
View All Bookings
 ↓
Check-In Customer
 ↓
Check-Out Customer
```

---

## 🔮 Future Enhancements

* Online Payment Gateway Integration
* Email Notifications
* PDF Bill Generation
* Dashboard Analytics
* Room Search & Filtering
* REST API Support
* Spring Boot Migration

---

## 👨‍💻 Developed By

**Mohan Krishna**

Java Enterprise Web Application Project

**MVC | Servlets | JSP | Hibernate | MySQL | Maven | Tomcat**
