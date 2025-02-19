package com.market.user.jpa.repository;

import com.market.user.jpa.entity.UserCustomerInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCustomerInfoRepository extends JpaRepository<UserCustomerInfoEntity, Long> {

}
