package com.attendance.login.UserPackage.repository;

import com.attendance.login.UserPackage.models.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryPerRepo extends JpaRepository<DeliveryPerson,String> {
   DeliveryPerson getByPhone(String phone);

    @Query("SELECT d FROM DeliveryPerson d WHERE d.verified LIKE '0'")
    List<DeliveryPerson> getPartners();
}
