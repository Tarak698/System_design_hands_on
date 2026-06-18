\# Day 1: Client-Server Architecture



\## Today's Goal



Today I learned the foundation of backend system design: client-server architecture.



I built a small Spring Boot server and called its APIs using a browser and the `curl` command.



\---



\## What is Client-Server Architecture?



Client-server architecture is a model where:



1\. A client sends a request.

2\. A server receives and processes the request.

3\. The server sends a response back to the client.



Simple flow:



```text

Client → Request → Server → Response → Client

```



\---



\## What is a Client?



A client is any application or system that requests data or functionality from a server.



Examples:



\* Web browser

\* Mobile application

\* Frontend website

\* Postman

\* `curl`

\* Another backend service



Example:



```text

Instagram mobile app asks the Instagram server for the user's feed.

```



In the Day 1 project:



```text

Browser and curl = Clients

```



\---



\## What is a Server?



A server is an application or computer that:



1\. Receives requests from clients.

2\. Processes those requests.

3\. Returns responses to clients.



Examples:



\* Instagram backend server

\* Amazon product server

\* Banking backend server

\* Spring Boot application



In the Day 1 project:



```text

Spring Boot application = Server

```



\---



\## Restaurant Example



Client-server architecture can be compared with a restaurant:



| Technical Concept | Restaurant Example            |

| ----------------- | ----------------------------- |

| Client            | Customer                      |

| Server            | Kitchen                       |

| API               | Menu                          |

| Request           | Customer's order              |

| Response          | Food returned to the customer |



The customer asks for food, and the kitchen prepares and returns it.



Similarly, a client sends a request, and the server processes and returns a response.



\---



\## What is a Request?



A request is a message sent by a client to a server.



A request can contain:



\* HTTP method

\* URL or endpoint

\* Headers

\* Request body

\* Path parameters



Example:



```text

GET /api/hello

```



This means the client is asking the server for the hello response.



Another example:



```text

POST /api/users

```



Request body:



```json

{

&#x20; "name": "Rahul",

&#x20; "role": "Java Developer"

}

```



This means the client is sending user data to the server.



\---



\## What is a Response?



A response is the answer returned by the server to the client.



A response can contain:



\* HTTP status code

\* Headers

\* JSON response body



Example response:



```json

{

&#x20; "message": "Hello Tarak, this response is coming from the server!"

}

```



Another response:



```json

{

&#x20; "id": 101,

&#x20; "name": "Rahul",

&#x20; "role": "Java Developer",

&#x20; "message": "User created successfully on server"

}

```



\---



\## What is an API?



API stands for Application Programming Interface.



An API provides a defined way for clients and servers to communicate.



An API endpoint is a specific URL exposed by the server.



Examples:



```text

/api/hello

/api/users/1

/api/users

/api/system-design

```



Simple meaning:



```text

The client uses an API endpoint to send a request to the server.

```



\---



\## APIs Built on Day 1



| Method | Endpoint             | Purpose                                   |

| ------ | -------------------- | ----------------------------------------- |

| GET    | `/api/hello`         | Receive a hello response from the server  |

| GET    | `/api/users/{id}`    | Get user details using an ID              |

| POST   | `/api/users`         | Send user data and create a user response |

| GET    | `/api/system-design` | Get the current learning topic            |



\---



\## GET Hello API



Request:



```text

GET /api/hello

```



Example URL:



```text

http://localhost:8080/api/hello

```



Response:



```json

{

&#x20; "message": "Hello Tarak, this response is coming from the server!",

&#x20; "server": "day-01-client-server",

&#x20; "time": "Current server time"

}

```



This API demonstrates a basic request and response.



\---



\## GET User API



Request:



```text

GET /api/users/1

```



Response:



```json

{

&#x20; "id": 1,

&#x20; "name": "Tarak Patra",

&#x20; "role": "Backend Engineer",

&#x20; "message": "User details fetched from server"

}

```



The value after `/users/` is dynamic.



Examples:



```text

/api/users/1

/api/users/10

/api/users/99

```



\---



\## POST User API



Request:



```text

POST /api/users

```



Request body:



```json

{

&#x20; "name": "Rahul",

&#x20; "role": "Java Developer"

}

```



Response:



```json

{

&#x20; "id": 101,

&#x20; "name": "Rahul",

&#x20; "role": "Java Developer",

&#x20; "message": "User created successfully on server"

}

```



This demonstrates how a client sends JSON data to a server.



\---



\## Important Spring Boot Annotations



\### `@RestController`



`@RestController` tells Spring Boot that the Java class exposes REST APIs.



Example:



```java

@RestController

public class ClientServerController {

}

```



Methods inside this class can receive HTTP requests and return JSON responses.



Simple meaning:



```text

@RestController makes the Java class capable of receiving API requests.

```



\---



\### `@RequestMapping`



`@RequestMapping` defines a common base path for APIs.



Example:



```java

@RequestMapping("/api")

```



If a method contains:



```java

@GetMapping("/hello")

```



The complete endpoint becomes:



```text

/api/hello

```



\---



\### `@GetMapping`



`@GetMapping` handles HTTP GET requests.



GET is generally used to retrieve data.



Example:



```java

@GetMapping("/hello")

```



This handles:



```text

GET /api/hello

```



\---



\### `@PostMapping`



`@PostMapping` handles HTTP POST requests.



POST is generally used to send or create data.



Example:



```java

@PostMapping("/users")

```



This handles:



```text

POST /api/users

```



\---



\### `@PathVariable`



`@PathVariable` reads a value from the URL.



Example:



```java

@GetMapping("/users/{id}")

public Map<String, Object> getUserById(@PathVariable Long id)

```



For this request:



```text

GET /api/users/10

```



The value of `id` becomes:



```text

id = 10

```



\---



\### `@RequestBody`



`@RequestBody` reads JSON data sent inside an HTTP request body.



Example:



```java

public ResponseEntity<?> createUser(

&#x20;       @RequestBody CreateUserRequest request

)

```



It reads JSON such as:



```json

{

&#x20; "name": "Rahul",

&#x20; "role": "Java Developer"

}

```



\---



\## What is JSON?



JSON stands for JavaScript Object Notation.



It is a common data format used for communication between clients and servers.



Example:



```json

{

&#x20; "id": 1,

&#x20; "name": "Tarak Patra",

&#x20; "role": "Backend Engineer"

}

```



A JSON object contains key-value pairs:



```text

"id"   = key

1      = value

```



\---



\## What is `localhost`?



`localhost` refers to the computer on which the application is currently running.



Example:



```text

http://localhost:8080

```



Meaning:



```text

Connect to the server running on my own computer.

```



\---



\## What is Port 8080?



A port identifies a particular application running on a computer.



The Spring Boot application was running on:



```text

Port 8080

```



The complete server address was:



```text

http://localhost:8080

```



\---



\## Internal Request Flow



When the browser called:



```text

http://localhost:8080/api/hello

```



The request flow was:



```text

Browser

&#x20;  ↓

HTTP request

&#x20;  ↓

Tomcat server

&#x20;  ↓

Spring DispatcherServlet

&#x20;  ↓

ClientServerController

&#x20;  ↓

Java method executes

&#x20;  ↓

JSON response

&#x20;  ↓

Browser

```



\---



\## What is Embedded Tomcat?



Spring Boot includes an embedded Tomcat web server.



When the application starts, Tomcat listens for requests on port `8080`.



The following log confirmed that the server started successfully:



```text

Tomcat started on port 8080

```



Because Tomcat is embedded, a separate Tomcat installation was not required.



\---



\## Browser and curl Testing



The browser was used as a client for GET requests.



Example:



```text

http://localhost:8080/api/hello

```



The `curl` command was used as a client for the POST request.



Example:



```cmd

curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\\"name\\":\\"Rahul\\",\\"role\\":\\"Java Developer\\"}"

```



The successful response proved that:



```text

Client sent JSON

Server received JSON

Server processed the request

Server returned JSON

```



\---



\## Important Difference



```text

Client = Sends the request

Server = Processes the request

API = Defines how they communicate

Request = Message sent to server

Response = Answer returned by server

```



\---



\## Interview Explanation



Client-server architecture is a model where a client, such as a browser, mobile application, frontend application, or another backend service, sends an HTTP request to a server.



The server receives the request, executes the required business logic, and returns an HTTP response, usually in JSON format.



For example, when a browser calls `GET /api/users/1`, the backend server receives the user ID, processes the request, and returns the corresponding user information.



\---



\## What I Built



I built a basic Spring Boot client-server application with:



\* A hello API

\* A get-user API

\* A create-user API

\* A system-design learning API

\* Path variable handling

\* JSON request handling

\* JSON response handling

\* Browser testing

\* `curl` testing



\---



\## Key Takeaway



```text

Client asks.

Server processes.

Server answers.

API is the communication path between them.

```



