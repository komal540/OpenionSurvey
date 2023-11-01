# Project README - Opinion Bureau Survey Management System

## Overview
Opinion Bureau Survey Management System is a Java-based application built using the Spring Boot framework. It utilizes SQLite as the database system and is managed with the Gradle build tool. Docker is used for containerization purposes. The project primarily focuses on the creation, management, and analysis of surveys.

## Project Features
1. **Survey Creation:** Users can create new surveys by providing necessary details such as survey title, description, and target audience.
2. **Question and Answer Management:** The system allows the addition, deletion, and modification of questions and answers within each survey.
3. **Survey Retrieval:** Surveys can be retrieved based on their unique identifiers (IDs) for easy access and analysis.
4. **Data Visualization:** The system provides visual representations of survey data through flowcharts and graphs.

## Technologies Used
- Java
- Spring Boot
- SQLite
- Gradle
- Docker

## Setup Instructions
1. Clone the repository from the provided link.
2. Ensure you have Java and Docker installed on your system.
3. Set up the SQLite database by running the provided scripts.
4. Build the project using Gradle.
5. Run the application using the provided commands.
6. Access the endpoints using the provided Postman requests.

## Architecture
The application follows a layered architecture consisting of the following components:
- **Controllers:** Handle incoming requests and manage the flow of data.
- **Services:** Implement business logic and process data.
- **Repositories:** Manage data access and interactions with the SQLite database.
- **Models:** Define the structure of the survey, questions, and answers.

## Usage
1. Use the provided endpoints for creating, managing, and retrieving surveys.
2. Leverage the data visualization features to analyze survey results effectively.
3. Refer to the documentation and comments within the code for detailed explanations of the application's functionalities.

## Contributors
- Komal Vashistha
