package com.attendance.login.Library.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelRepo extends JpaRepository<DeliveryPartner,String> {
}
