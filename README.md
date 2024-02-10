# Cinema Room REST Service

## About

"A simple Spring REST service that will help you manage a small movie theater. 
It will handle HTTP requests in controllers, create services, and respond with JSON objects.
Make good use of Spring and write a REST service that can show the available seats, 
sell and refund tickets, and display the statistics of your venue." [1]

## Install and Run

- Install Maven
    - Download and install: https://maven.apache.org/install.html
- `mvn exec:java` will start the program.

## User Interaction
The REST framework is used for interacting with the application.
- View available seats to purchase
  - GET http://localhost:8080/seats
- View statistics about purchased and available seats.
  - GET http://localhost:8080/stats?password=super_secret
- Purchase a ticket
  - POST http://localhost:28852/purchase
  - Populate the body of the request with JSON for the row and column of the seat.
  - The purchased ticket details will be returned. The token is unique and used to refund a ticket.

Body of POST request
```json
{
    "row": 1,
    "column": 1
}
```

Response for POST request
```json
{
    "token": "706f1ea5-d80d-4ea4-ae13-9bb388e2e170",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```
- Return a ticket.
  - POST http://localhost:8080/return
  - Populate the body with the JSON of the ticket token.

Body of POST request
```json
{
    "token": "7680083c-c8db-4647-87e8-9072e2d4811d"
}
```


##  References

1. [Cinema Room Rest Service with Java Hyperskill project](https://hyperskill.org/projects/189)