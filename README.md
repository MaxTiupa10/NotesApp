# 📝 The Notes App

Test assignment implementation using **Spring Boot** and **MongoDB**.
The application allows users to create, read, update, delete, and analyze notes within a Dockerized environment.

## 🛠 Tech Stack
- **Java 17**
- **Spring Boot 3.3**
- **MongoDB**
- **Docker & Docker Compose**
- **Maven**

---

## 🚀 How to Run

### Prerequisites
- Docker & Docker Compose installed.
- Maven (optional, you can use the wrapper `./mvnw`).

### Steps

1. **Build the application:**
   Open a terminal in the project root and run:
   ```bash
   mvn clean package -DskipTests
Windows users can also use:
   .\mvnw clean package -DskipTests
2.Run with Docker Compose:
   ```bash
   docker-compose up --build
   ```
3.Access the application: The API will be available at:
   ```bash
   http://localhost:8080/api/notes
   ```
4.Stop the application: Press Ctrl+C or run:
   ```bash
  docker-compose down
   ```
## 🧪 Quick Start Data (JSON Examples)

You can populate the database using Postman or cURL.

Endpoint: POST http://localhost:8080/api/notes

Content-Type: application/json

1. Standard Note (Test Filtering)

Tag: BUSINESS
```bash
{
  "title": "Project Roadmap Q4",
  "text": "Finalize the API documentation and set up CI/CD pipelines for the backend team.",
  "tags": ["BUSINESS"]
}
```
2. Word Stats Test

Tag: PERSONAL Use this to test GET /api/notes/{id}/stats. The word 'coffee' appears 3 times.
```bash
{
  "title": "Morning Routine",
  "text": "Need to buy coffee. Coffee is life. Buy milk and coffee again.",
  "tags": ["PERSONAL"]
}
```
3. Important Alert (Test Sorting)

Tag: IMPORTANT This note should appear at the top of the list (Sorted by Created Date DESC).
```bash
{
  "title": "SECURITY ALERT",
  "text": "Never commit secrets or passwords to the Git repository! Use environment variables.",
  "tags": ["IMPORTANT"]
}
```
4. Multi-tag Note

Tests support for multiple tags.
```bash
{
  "title": "Startup Idea",
  "text": "Create an AI-based note sorting algorithm using Spring AI.",
  "tags": ["BUSINESS", "PERSONAL"]
}
```
5. Empty Tags Note

Tests creation with an empty tag list.
```bash
{
  "title": "Just a thought",
  "text": "Today is a great day to learn Docker and MongoDB.",
  "tags": []
}
```
## 📡 API Endpoints Reference

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/notes` | Create a new note |
| `GET` | `/api/notes` | Get list of notes (Summary: ID, Title, Date) |
| `GET` | `/api/notes/{id}` | Get full note details (includes Text) |
| `PUT` | `/api/notes/{id}` | Update a note |
| `DELETE` | `/api/notes/{id}` | Delete a note |
| `GET` | `/api/notes/{id}/stats` | Get word frequency statistics (Unique words, sorted DESC) |

Query Parameters (For Listing)

    Pagination: ?page=0&size=10

    Filtering: ?tag=BUSINESS (Allowed tags: BUSINESS, PERSONAL, IMPORTANT)

## Results

<img width="1650" height="864" alt="зображення" src="https://github.com/user-attachments/assets/93e5f30c-04ea-4d59-b8e6-2f3e4c378cfa" />

<img width="1886" height="1113" alt="зображення" src="https://github.com/user-attachments/assets/c238d7c7-a0a8-4235-84d3-d48519ae6600" />

<img width="1954" height="1134" alt="зображення" src="https://github.com/user-attachments/assets/0ccbfc5a-b9e0-421e-97de-962e2a756579" />

<img width="1917" height="958" alt="зображення" src="https://github.com/user-attachments/assets/0296905f-5432-4f41-a58c-cbd187dad9ff" />

<img width="1912" height="847" alt="зображення" src="https://github.com/user-attachments/assets/c35e5fef-7ddb-4c0a-8863-55934199b8dc" />

<img width="608" height="427" alt="зображення" src="https://github.com/user-attachments/assets/57792b60-7209-4df8-98cd-9e202249a850" />

