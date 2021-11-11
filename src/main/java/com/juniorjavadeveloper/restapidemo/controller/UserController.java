package com.juniorjavadeveloper.restapidemo.controller;

import com.juniorjavadeveloper.restapidemo.exception.ErrorDetails;
import com.juniorjavadeveloper.restapidemo.model.User;
import com.juniorjavadeveloper.restapidemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Tag(name = "user", description = "The user API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Creates new user", description = "Provide user with a unique username and email.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "Data already exist",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @Operation(summary = "Returns user by id", description = "Throws exception if user is not found.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User doesn't exist",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUser(
            @Parameter(description = "Id to retrieve user") @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @Operation(summary = "Returns user list", description = "All users in our database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user list.",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    })
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @Operation(summary = "Deletes user by id", description = "Throws exception if user not found.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully", content = { @Content(schema = @Schema(hidden = true))}),
            @ApiResponse(responseCode = "404", description = "User doesn't exist",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Id to delete user") @PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
