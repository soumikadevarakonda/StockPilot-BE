# 🪙 StockPilot Backend

### 🚀 A Virtual Stock Trading Simulation App built with Spring Boot and MySQL

StockPilot lets users **register, login, buy & sell stocks, and track their portfolio & transactions** — simulating a real-world stock trading platform.
This repository contains the **Spring Boot backend** APIs powering the system.

---

## ⚙️ Tech Stack

* **Backend:** Spring Boot 3.x (Java 17)
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security + JWT
* **Build Tool:** Maven
* **IDE:** Eclipse

---

## 🧩 Architecture Overview

```
+-------------------+
|     Frontend      | (React / Next.js - Future Integration)
+---------+---------+
          |
          v
+-------------------+
|   Spring Boot     |
|  (REST APIs)      |
+-------------------+
| AuthController    | → Register, Login (JWT)
| StockController   | → Add, List, Update Stocks
| PortfolioController | → Buy, Sell, View Holdings
| TransactionController | → History of all Buy/Sell
+-------------------+
| Service Layer     | → Business Logic
| Repository Layer  | → JPA Database Access
+-------------------+
          |
          v
+-------------------+
|     MySQL DB      |
| users, stocks,    |
| portfolio,        |
| transactions      |
+-------------------+
```

---

## 🧱 Database Schema

| Table            | Purpose                                       |
| ---------------- | --------------------------------------------- |
| **users**        | Stores user details (email, password, name)   |
| **stocks**       | Stores stock name, symbol, and price          |
| **portfolio**    | Maps users to their owned stocks & quantities |
| **transactions** | Stores every buy/sell action with timestamp   |

---

## 🔐 Authentication

* JWT-based authentication using Spring Security
* Passwords hashed with **BCrypt**
* Token returned on successful login

---

## 📡 API Endpoints

### 🧟 User Auth

| Method | Endpoint             | Description             |
| ------ | -------------------- | ----------------------- |
| `POST` | `/api/auth/register` | Register new user       |
| `POST` | `/api/auth/login`    | Login and get JWT token |

---

### 📈 Stocks

| Method | Endpoint                                | Description        |
| ------ | --------------------------------------- | ------------------ |
| `GET`  | `/api/stocks`                           | Get all stocks     |
| `POST` | `/api/stocks`                           | Add a new stock    |
| `PUT`  | `/api/stocks/{symbol}?price={newPrice}` | Update stock price |

---

### 💼 Portfolio

| Method | Endpoint                 | Description                  |
| ------ | ------------------------ | ---------------------------- |
| `POST` | `/api/portfolio/buy`     | Buy a stock                  |
| `POST` | `/api/portfolio/sell`    | Sell a stock                 |
| `GET`  | `/api/portfolio/{email}` | Get user’s current portfolio |

---

### 📜 Transactions

| Method | Endpoint                    | Description                             |
| ------ | --------------------------- | --------------------------------------- |
| `GET`  | `/api/transactions/{email}` | Get all transactions (buy/sell history) |

---

## 🌐 Deployment

- Backend API: https://stockpilot-backend-1qox.onrender.com
- Frontend: https://stockpilot-one-sigma.vercel.app
- Database: Neon PostgreSQL

---

## 🧠 Future Enhancements

* ✅ JWT-based route protection (only logged-in users access portfolio)
* 📊 Live stock data API integration (AlphaVantage / Yahoo Finance)
* 🧮 Profit & Loss calculation
* 🏆 Leaderboard for top traders
* 📈 Chart analytics on frontend

---

## 💻 Running Locally

### 1️⃣ Clone the repo

```bash
git clone https://github.com/soumikadevarakonda/StockPilot-BE.git
cd StockPilot-BE
```
