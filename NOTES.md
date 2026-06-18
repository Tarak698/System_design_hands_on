**# Day 2: HTTP, REST API and Status Codes**



**## Today's Goal**



**Today I learned how a client communicates with a server using HTTP methods and how a server communicates the result using HTTP status codes.**



**I built a User CRUD REST API using Spring Boot.**



**---**



**## What is HTTP?**



**HTTP stands for Hypertext Transfer Protocol.**



**It is the communication protocol used between a client and a server.**



**The client sends an HTTP request, and the server sends an HTTP response.**



**Example:**



**```text**

**Client request:**

**GET /api/users/1**



**Server response:**

**200 OK**



**{**

&#x20; **"id": 1,**

&#x20; **"name": "Tarak Patra",**

&#x20; **"role": "Backend Engineer"**

**}**

**```**



**---**



**## What is REST?**



**REST is a style of designing APIs around resources.**



**In this project, the resource is:**



**```text**

**User**

**```**



**The base URL is:**



**```text**

**/api/users**

**```**



**Different HTTP methods perform different operations on this resource.**



**---**



**## What is CRUD?**



**CRUD stands for:**



**| Letter | Operation | HTTP Method |**

**| ------ | --------- | ----------- |**

**| C      | Create    | POST        |**

**| R      | Read      | GET         |**

**| U      | Update    | PUT         |**

**| D      | Delete    | DELETE      |**



**---**



**## HTTP Methods**



**### GET**



**GET is used to read or fetch data from the server.**



**Examples:**



**```text**

**GET /api/users**

**```**



**Returns all users.**



**```text**

**GET /api/users/1**

**```**



**Returns the user whose ID is `1`.**



**GET requests normally do not contain a request body.**



**---**



**### POST**



**POST is used to create a new resource.**



**Example:**



**```text**

**POST /api/users**

**```**



**Request body:**



**```json**

**{**

&#x20; **"name": "Amit",**

&#x20; **"role": "Spring Boot Developer"**

**}**

**```**



**The server creates a new user and returns:**



**```text**

**201 Created**

**```**



**---**



**### PUT**



**PUT is used to update an existing resource.**



**Example:**



**```text**

**PUT /api/users/1**

**```**



**Request body:**



**```json**

**{**

&#x20; **"name": "Tarak Patra",**

&#x20; **"role": "Senior Backend Engineer"**

**}**

**```**



**The server replaces the existing user information with the new information.**



**A successful update returns:**



**```text**

**200 OK**

**```**



**---**



**### DELETE**



**DELETE is used to remove an existing resource.**



**Example:**



**```text**

**DELETE /api/users/2**

**```**



**When the user is deleted successfully, the server returns:**



**```text**

**204 No Content**

**```**



**There is no response body because the resource has already been removed.**



**---**



**## APIs Built Today**



**| Method | Endpoint          | Purpose                 | Possible Status Codes |**

**| ------ | ----------------- | ----------------------- | --------------------- |**

**| GET    | `/api/users`      | Get all users           | 200                   |**

**| GET    | `/api/users/{id}` | Get one user by ID      | 200, 404              |**

**| POST   | `/api/users`      | Create a new user       | 201, 400              |**

**| PUT    | `/api/users/{id}` | Update an existing user | 200, 400, 404         |**

**| DELETE | `/api/users/{id}` | Delete an existing user | 204, 404              |**



**---**



**## HTTP Status Codes**



**### 200 OK**



**Meaning:**



**```text**

**The request was successful.**

**```**



**Used for:**



**\* Fetching all users**

**\* Fetching one user**

**\* Updating a user**



**Example:**



**```java**

**return ResponseEntity.ok(user);**

**```**



**---**



**### 201 Created**



**Meaning:**



**```text**

**A new resource was created successfully.**

**```**



**Used after creating a user with POST.**



**Example:**



**```java**

**return ResponseEntity**

&#x20;       **.status(HttpStatus.CREATED)**

&#x20;       **.body(user);**

**```**



**---**



**### 204 No Content**



**Meaning:**



**```text**

**The request was successful, but there is no response body.**

**```**



**Used after successfully deleting a user.**



**Example:**



**```java**

**return ResponseEntity.noContent().build();**

**```**



**---**



**### 400 Bad Request**



**Meaning:**



**```text**

**The client sent invalid or incomplete data.**

**```**



**Examples:**



**\* Name is empty**

**\* Role is empty**

**\* Required input is missing**

**\* Request body is invalid**



**Example response:**



**```json**

**{**

&#x20; **"status": 400,**

&#x20; **"error": "Bad Request",**

&#x20; **"message": "Name is required"**

**}**

**```**



**A 400 error is generally a client-side mistake.**



**---**



**### 404 Not Found**



**Meaning:**



**```text**

**The requested resource does not exist.**

**```**



**Example:**



**```text**

**GET /api/users/99**

**```**



**If user `99` does not exist, the server returns:**



**```json**

**{**

&#x20; **"status": 404,**

&#x20; **"error": "Not Found",**

&#x20; **"message": "User not found with id: 99"**

**}**

**```**



**---**



**### 500 Internal Server Error**



**Meaning:**



**```text**

**An unexpected error occurred inside the server.**

**```**



**Examples:**



**\* Unhandled exception**

**\* Null pointer exception**

**\* Database connection failure**

**\* Unexpected application failure**



**A 500 error is generally a server-side problem.**



**Expected validation problems should return 400, not 500.**



**---**



**## Important Spring Boot Annotations**



**### `@RestController`**



**Marks the class as a REST API controller.**



**The methods return data such as JSON directly to the client.**



**---**



**### `@RequestMapping("/api/users")`**



**Defines the common base URL for all APIs inside the controller.**



**---**



**### `@GetMapping`**



**Handles GET requests.**



**Example:**



**```java**

**@GetMapping("/{id}")**

**```**



**---**



**### `@PostMapping`**



**Handles POST requests.**



**Used to create new data.**



**---**



**### `@PutMapping`**



**Handles PUT requests.**



**Used to update existing data.**



**---**



**### `@DeleteMapping`**



**Handles DELETE requests.**



**Used to delete data.**



**---**



**### `@PathVariable`**



**Reads a dynamic value from the URL.**



**Example:**



**```text**

**/api/users/10**

**```**



**In this request:**



**```java**

**@PathVariable Long id**

**```**



**receives:**



**```text**

**id = 10**

**```**



**---**



**### `@RequestBody`**



**Reads JSON data sent by the client in the HTTP request body.**



**Example:**



**```json**

**{**

&#x20; **"name": "Amit",**

&#x20; **"role": "Java Developer"**

**}**

**```**



**---**



**## What is `ResponseEntity`?**



**`ResponseEntity` allows the server to control:**



**\* HTTP status code**

**\* Response body**

**\* Response headers**



**Example:**



**```java**

**return ResponseEntity.ok(user);**

**```**



**This returns:**



**```text**

**200 OK**

**```**



**with the user as the response body.**



**Another example:**



**```java**

**return ResponseEntity**

&#x20;       **.status(HttpStatus.CREATED)**

&#x20;       **.body(user);**

**```**



**This returns:**



**```text**

**201 Created**

**```**



**with the newly created user.**



**---**



**## Temporary Data Storage**



**In this project, users are stored inside:**



**```java**

**Map<Long, User>**

**```**



**This is in-memory storage.**



**It means:**



**```text**

**Application running = data is available**

**Application restarted = newly added data is lost**

**```**



**This project does not use a real database yet.**



**A database will be added in the next phase of learning.**



**---**



**## Request and Response Example**



**### Request**



**```text**

**POST /api/users**

**Content-Type: application/json**

**```**



**```json**

**{**

&#x20; **"name": "Amit",**

&#x20; **"role": "Java Developer"**

**}**

**```**



**### Response**



**```text**

**201 Created**

**```**



**```json**

**{**

&#x20; **"id": 3,**

&#x20; **"name": "Amit",**

&#x20; **"role": "Java Developer"**

**}**

**```**



**---**



**## Layman Example**



**Think about ordering food in a restaurant:**



**| HTTP Concept | Restaurant Example                         |**

**| ------------ | ------------------------------------------ |**

**| GET          | Ask to see an existing order               |**

**| POST         | Place a new order                          |**

**| PUT          | Change the existing order                  |**

**| DELETE       | Cancel the order                           |**

**| 200          | Request completed successfully             |**

**| 201          | New order created                          |**

**| 400          | Customer gave invalid order details        |**

**| 404          | Order number does not exist                |**

**| 500          | Kitchen has an unexpected internal problem |**



**---**



**## Important Difference Between 400, 404 and 500**



**```text**

**400 = Client sent invalid data**

**404 = Requested data does not exist**

**500 = Server experienced an unexpected failure**

**```**



**Examples:**



**```text**

**Empty user name       → 400**

**User ID does not exist → 404**

**Application crashes   → 500**

**```**



**---**



**## Interview Explanation**



**REST is an architectural style for designing APIs around resources. HTTP methods represent the operation being performed on a resource. GET is used to retrieve data, POST is used to create data, PUT is used to update data, and DELETE is used to remove data.**



**A well-designed API returns meaningful HTTP status codes. For example, 200 represents a successful request, 201 represents successful resource creation, 400 represents invalid client input, 404 represents a missing resource, and 500 represents an unexpected server-side failure.**



**---**



**## What I Built**



**I built a User CRUD REST API with:**



**\* Get all users**

**\* Get a user by ID**

**\* Create a new user**

**\* Update an existing user**

**\* Delete a user**

**\* Input validation**

**\* Proper HTTP status handling**

**\* JSON request and response bodies**



**---**



**## Key Takeaway**



**```text**

**GET means read.**

**POST means create.**

**PUT means update.**

**DELETE means delete.**



**200 means success.**

**201 means created.**

**204 means success with no response body.**

**400 means invalid client request.**

**404 means resource not found.**

**500 means unexpected server error.**

**```**



