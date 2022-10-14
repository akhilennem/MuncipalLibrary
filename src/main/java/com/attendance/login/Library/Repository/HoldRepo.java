package com.attendance.login.Library.Repository;

import com.attendance.login.Library.Models.Hold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

@Transactional
public interface HoldRepo extends JpaRepository<Hold,Integer> {

    Void deleteByAccessionno(String accessionno);

    void deleteByHoldid(String holdid);

    Hold getByAccessionno(String accessionno);
}
