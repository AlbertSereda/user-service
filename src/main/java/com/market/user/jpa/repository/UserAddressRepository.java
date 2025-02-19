package com.market.user.jpa.repository;

import com.market.user.jpa.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

}
