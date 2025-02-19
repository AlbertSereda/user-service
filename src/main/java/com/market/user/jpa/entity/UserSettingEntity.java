package com.market.user.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_setting")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User UI settings")
public class UserSettingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Setting Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Owner of user settings")
    private UserEntity user;

    @Column(name = "setting", columnDefinition = "jsonb")
    @Schema(description = "JSON settings")
    private String setting;
}
