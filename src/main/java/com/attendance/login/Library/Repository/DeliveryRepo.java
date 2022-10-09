package com.attendance.login.Library.Repository;

import com.attendance.login.Library.Models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<Delivery,String> {
    boolean existsByAccessionno(String accessionno);

    Delivery getByAccessionno(String accessionno);
}
