package com.attendance.login.OtpSender.OtpRepository;

import com.attendance.login.OtpSender.OtpVerifier.OtpVerifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpVerifier,Integer> {
    boolean existsByOtpAndPhoneoremail(String otp, String phoneoremail);


    void deleteByPhoneoremail(String phoneoremail);


}
