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
@Table(name = "user_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Table storing user addresses")
public class UserAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "User address id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_customer_info_id", nullable = false)
    @Schema(description = "Reference to the user_customer_info table")
    private UserCustomerInfoEntity userCustomerInfo;

    @Column(name = "address", nullable = false)
    @Schema(description = "Full address")
    private String address;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Schema(description = "Timestamp when the address was created")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Schema(description = "Timestamp when the address was last updated")
    private LocalDateTime updateDate;

    @Column(name = "is_archive", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Schema(description = "Indicates if the address is archived")
    private Boolean isArchive = false;
}
