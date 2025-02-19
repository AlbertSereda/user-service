package com.market.user.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_seller_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User seller info")
public class UserSellerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Setting Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Owner of user settings")
    private UserEntity user;

    @Column(name = "inn", length = 10, unique = true)
    @Schema(description = "Taxpayer identification number")
    private String inn;

    @Column(name = "rating", precision = 2, scale = 1)
    @Schema(description = "Seller rating")
    private Double rating;

    @Column(name = "company_name", unique = true, nullable = false)
    @Schema(description = "Name of the company")
    private String companyName;

    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @UpdateTimestamp
    @Schema(description = "Timestamp of the last update")
    private LocalDateTime updateDate;
}
