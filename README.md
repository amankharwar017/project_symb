## Smart Parking Lot System ##
A Full Stack Smart Parking Lot Management System built using:
Backend: Spring Boot + MySQL
Frontend: React (Create React App)
Architecture: REST APIs
Database: MySQL

This project was developed as an assignment to demonstrate full-stack development, REST API design, and nearest-slot allocation logic.

## Features
1️-> Add Parking Slot
Create new parking slots
Configure:
Covered (Yes/No)
EV Charging (Yes/No)
Slot is initially marked as occupied = false

2️-> View Parking Slots
Displays all parking slots in tabular format
Shows:
Slot Number
Covered
EV Charging
Occupied Status

3️-> Park Vehicle (Nearest Allocation Logic)
Select vehicle requirements:
Needs EV Charging
Needs Covered Slot
System:
Filters matching available slots
Allocates the nearest slot (lowest slot number)
Marks slot as occupied = true

4️-> Remove Vehicle
Enter slot number
System:
Validates slot exists
Checks if slot is occupied
Marks slot as occupied = false

5️-> Output Display Panel
Displays:
Success messages
Validation errors
System responses

# Project Structure
Backend (Spring Boot)
backend/
 ├── controller/
 │     └── ParkingController.java
 ├── service/
 │     ├── ParkingService.java
 │     └── ParkingServiceImpl.java
 ├── entity/
 │     └── ParkingSlot.java
 ├── repository/
 │     └── ParkingSlotRepo.java
 ├── exception/
 │     └── SimpleParkingException.java
 ├── application.properties
 └── SmartParkingApplication.java

Frontend (React)
frontend/parking_frontend/
 ├── src/
 │    ├── pages/
 │    │     └── AssignmentUI.js
 │    ├── App.js
 │    ├── App.css
 │    └── index.js
 ├── package.json

# Technologies Used
Backend =>
  Java 17
  Spring Boot
  Spring Data JPA
  MySQL
  REST APIs
  
Frontend =>
  React (Create React App)
  React Router DOM
  useEffect Hook
  Async/Await (Fetch API)

#-> Allocation Logic
   The system allocates parking slots based on:
   Slot must not be occupied
   If EV required → slot must support EV charging
   If Covered required → slot must be covered
   Among matching slots → lowest slot number is selected

#-> Validation Rules
   Duplicate slot numbers are not allowed
   Cannot remove vehicle from a free slot
   Cannot park when no matching slot is available
   Slot number must exist

=> API Endpoints
   Method	Endpoint	Description
   POST	/api/slots	Add new slot
   GET	/api/slots	Get all slots
   POST	/api/slots/park?needsEV=true&needsCover=false	Park vehicle
   POST	/api/slots/remove/{slotNo}	Remove vehicle
   
-> Setup Instructions
-> Backend Setup


#-> Create MySQL Database
CREATE DATABASE parkingdb;

2️-> Configure application.properties
  spring.datasource.url=jdbc:mysql://localhost:3306/parkingdb
  spring.datasource.username=root
  spring.datasource.password=YOUR_PASSWORD
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  server.port=8080

3️-> Run Backend
mvn spring-boot:run


Backend runs on: http://localhost:8080

-> Frontend Setup
    Navigate to:
    frontend/parking_frontend


Then run:   npm install
            npm start


Frontend runs on:  http://localhost:3000
