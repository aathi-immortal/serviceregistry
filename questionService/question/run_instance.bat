@echo off

REM Change directory to your project folder (adjust the path if needed)
cd /d "C:\Users\User\Desktop\Quiz microservices\quizService\question"

@REM REM Start first instance on port 8080
@REM call mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8080" &

@REM REM Wait for a few seconds (e.g., 5 seconds) before starting the second instance
@REM timeout /t 5 /nobreak

REM Start second instance on port 8081
call mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
