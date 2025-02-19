package com.market.user.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "User Id")
    private Long id;

    @Column(name = "email", length = 100)
    @Schema(description = "User email")
    private String email;

    @Column(name = "phone_number", nullable = false, length = 100)
    @Schema(description = "User phone number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @Schema(description = "User type id")
    private UserTypeEntity userType;

    @Column(name = "creation_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @CreationTimestamp
    @Schema(description = "User creation date")
    private LocalDateTime creationDate;

    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @UpdateTimestamp
    @Schema(description = "User update date")
    private LocalDateTime updateDate;

    @Column(name = "is_archive", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Schema(description = "Indicates whether the user is archived: true - archived, false - active")
    private Boolean isArchive = false;

    @Column(name = "is_blocked", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Schema(description = "Indicates whether the user is blocked: true - blocked, false - active")
    private Boolean isBlocked = false;

    @Column(name = "blocked_reason")
    @Schema(description = "The reason for the user blocking")
    private String blockedReason;
}
