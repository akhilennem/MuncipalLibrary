package com.attendance.login.UserPackage.repository;

import com.attendance.login.UserPackage.models.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPerRepo extends JpaRepository<DeliveryPerson,String> {
   DeliveryPerson getByPhone(String phone);
}
