This is a simple Dockerized REST API banking application with PostgreSQL as the database.
It includes all basic CRUD operations, as well as depositing and withdrawing amounts.
Exception handling has also been implemented.

Before running the application in Docker, ensure that you have packaged it and skipped the tests using the command: mvnw clean package -DskipTests.

Additionally, it's crucial to note that users must adjust all passwords in the docker-compose file according to their specific setup.