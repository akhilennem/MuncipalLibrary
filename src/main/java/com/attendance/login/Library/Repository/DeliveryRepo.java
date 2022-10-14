package com.attendance.login.Library.Repository;

import com.attendance.login.Library.Models.Delivery;
import com.attendance.login.UserPackage.models.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepo extends JpaRepository<Delivery,String> {
    boolean existsByAccessionno(String accessionno);

    Delivery getByAccessionno(String accessionno);


}
