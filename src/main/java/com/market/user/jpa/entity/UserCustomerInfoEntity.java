package com.market.user.jpa.entity;

import com.market.user.consts.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_customer_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User customer info")
public class UserCustomerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Customer id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Reference to the user")
    private UserEntity user;

    @Column(name = "first_name", nullable = false, length = 100)
    @Schema(description = "User's first name")
    private String firstName;

    @Column(name = "last_name", length = 100)
    @Schema(description = "User's last name")
    private String lastName;

    @Column(name = "middle_name", length = 100)
    @Schema(description = "User's middle name")
    private String middleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    @Schema(description = "User's gender")
    private GenderEnum sex;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Schema(description = "Timestamp of the last update")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "userCustomerInfo")
    @Schema(description = "Customer's addresses")
    private List<UserAddressEntity> addressEntityList;
}
