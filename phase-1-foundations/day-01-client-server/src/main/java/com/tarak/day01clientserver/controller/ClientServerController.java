package com.tarak.day01clientserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClientServerController {

    @GetMapping("/hello")
    public Map<String, Object> helloFromServer() {
        return Map.of(
                "message", "Hello Tarak, this response is coming from the server!",
                "server", "day-01-client-server",
                "time", LocalDateTime.now().toString()
        );
    }

    @GetMapping("/users/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        return Map.of(
                "id", id,
                "name", "Tarak Patra",
                "role", "Backend Engineer",
                "message", "User details fetched from server"
        );
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody CreateUserRequest request) {
        Map<String, Object> response = Map.of(
                "id", 101,
                "name", request.name(),
                "role", request.role(),
                "message", "User created successfully on server"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/system-design")
    public Map<String, Object> systemDesign() {
        return Map.of(
                "topic", "Client-Server Architecture",
                "day", 1,
                "status", "Learning started"
        );
    }

    public record CreateUserRequest(String name, String role) {
    }
}
