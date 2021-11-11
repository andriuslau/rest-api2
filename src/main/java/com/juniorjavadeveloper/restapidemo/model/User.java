package com.juniorjavadeveloper.restapidemo.model;

import com.juniorjavadeveloper.restapidemo.validation.UserName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Schema(maxLength = 255, example = "John")
    @NotBlank(message = "First name is required and can't be blank.")
    @Size(max = 255, message = "First name can't be more than 255 symbols.")
    private String firstName;

    @Schema(maxLength = 255, example = "Doe")
    @NotBlank(message = "Last name is required and can't be blank.")
    @Size(max = 255, message = "Last name can't be more than 255 symbols.")
    private String lastName;

    @Schema(minLength = 3, maxLength = 255, description = "Unique username without spaces", example = "amazinguser")
    @NotNull(message = "Username is required.")
    @UserName
    @Column(nullable = false, unique = true)
    private String userName;

    @Schema(description = "Valid email address is required", example = "johndoe@example.com")
    @NotBlank(message = "Email is required and can't be blank.")
    @Email(message = "Enter valid email.")
    @Column(nullable = false, unique = true)
    private String email;

    @Schema(example = "MALE")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
