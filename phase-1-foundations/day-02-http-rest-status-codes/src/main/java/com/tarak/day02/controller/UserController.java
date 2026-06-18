package com.tarak.day02.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(3);

    public UserController() {
        users.put(1L, new User(1L, "Tarak Patra", "Backend Engineer"));
        users.put(2L, new User(2L, "Rahul", "Java Developer"));
    }

    // READ: Get all users
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(users.values());
    }

    // READ: Get one user
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = users.get(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User not found with id: " + id
                    ));
        }

        return ResponseEntity.ok(user);
    }

    // CREATE: Create new user
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody CreateUserRequest request
    ) {
        if (request.name() == null || request.name().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "Name is required"
                    ));
        }

        if (request.role() == null || request.role().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "Role is required"
                    ));
        }

        Long id = idGenerator.getAndIncrement();

        User user = new User(
                id,
                request.name(),
                request.role()
        );

        users.put(id, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    // UPDATE: Update existing user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        User existingUser = users.get(id);

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User not found with id: " + id
                    ));
        }

        if (request.name() == null || request.name().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "Name is required"
                    ));
        }

        if (request.role() == null || request.role().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "Role is required"
                    ));
        }

        User updatedUser = new User(
                id,
                request.name(),
                request.role()
        );

        users.put(id, updatedUser);

        return ResponseEntity.ok(updatedUser);
    }

    // DELETE: Delete existing user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User removedUser = users.remove(id);

        if (removedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User not found with id: " + id
                    ));
        }

        return ResponseEntity.noContent().build();
    }

    public record User(
            Long id,
            String name,
            String role
    ) {
    }

    public record CreateUserRequest(
            String name,
            String role
    ) {
    }

    public record UpdateUserRequest(
            String name,
            String role
    ) {
    }
}
