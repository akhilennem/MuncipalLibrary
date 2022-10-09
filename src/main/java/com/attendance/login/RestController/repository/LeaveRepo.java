package com.attendance.login.RestController.repository;

import com.attendance.login.RestController.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface LeaveRepo extends JpaRepository<Leave,String> {

    boolean existsByEmail(String email);


    void deleteByEmail(String email);
}
