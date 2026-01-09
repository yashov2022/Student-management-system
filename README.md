 Student API with Ollama Integration:
A Spring Boot REST API that manages student records using MySQL + JDBC and generates AI-based student summaries using Ollama (local LLM).
This project demonstrates backend development, database integration, and local AI model usage without relying on cloud APIs.
 Features of this project are:
CRUD operations for Students
MySQL database integration using JdbcTemplate
AI-generated student summaries using Ollama
RESTful API design
Local LLM execution (no cloud dependency)

Tech Stack used:

| Layer        | Technology                   |
| ------------ | ---------------------------- |
| Backend      | Java 17                      |
| Framework    | Spring Boot                  |
| Database     | MySQL                        |
| DB Access    | Spring JDBC (`JdbcTemplate`) |
| AI / LLM     | Ollama (LLaMA 3)             |
| Build Tool   | Maven                        |
| Testing Tool | Postman                      |

Ports Used are:
| Service           | Port      |
| ----------------- | --------- |
| Spring Boot API   | 8082 |
| Ollama LLM Server | 11434 |
| MySQL             | 3306  |


