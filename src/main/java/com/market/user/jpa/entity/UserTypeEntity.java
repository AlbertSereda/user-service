package com.market.user.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User type")
public class UserTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Type Id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    @Schema(description = "Type name")
    private String name;

    @Column(name = "is_archive", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Schema(description = "Indicates whether the type is archived: true - archived, false - active")
    private Boolean isArchive = false;
}
