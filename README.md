# Doc

How to Start the System

To start the system, use Docker Compose to build and run the microservices. Follow these steps:

    #Clone the repository
    #docker-compose up --build

This command will build the Docker images for the microservices and start them up. Once the services are up and running, you can interact with them via their exposed REST APIs.

System Overview

The system is composed of five microservices, each with a specific role to play in handling client requests, processing data, and storing information. These services are:

    ServiceS1: Accepts client requests, verifies authentication, and sends data to a queue using JMS.
    Service2-5: Retrieve mock data from each other.
    ListenerService5: Receives messages from the queue, processes them, and stores the data in a database.

The system uses JMS (Java Message Service) for asynchronous message exchange between microservices. This architecture ensures loose coupling, flexibility, and scalability.
ServiceS1 Documentation
Description

The ServiceS1 microservice provides a REST API to accept client requests. It verifies the sid header for authentication and sends the received clientId data to clientId.queue via JMS.
API

    Method: POST
    URL: /service1/call
    Headers:
        sid: Authentication token (must be valid_sid).
    Request Body:

{
  "clientId": "123"
}

    Response:
        On success: "Client id has been sent."
        On authentication error: "Unauthorized"

Improvements

    Logging:
    Add logging for incoming requests and their processing results. This will help track issues and analyze system performance.

    JMS Error Handling:
    Add exception handling for message sending to the queue. For example, if the broker is unavailable, retry sending the message or return an error to the client.

ListenerService5 Documentation
Description

The ListenerService5 microservice receives messages from the clinetContacts.queue, processes them, and stores the data in the database. It uses JMS for asynchronous message consumption.
Improvements

    Logging:
    Add logging for received messages and their processing results.

    Database Error Handling:
    Add exception handling for database operations. For example, if the database is unavailable, retry the operation or store the message in a queue for reprocessing.

Unit Tests

    ServiceS1Controller
    Test cases for the ServiceS1 controller to ensure correct handling of requests and proper authentication.

    ListenerService5
    Test cases for the ListenerService5 to verify that the messages are received, processed, and stored in the database correctly.

      
