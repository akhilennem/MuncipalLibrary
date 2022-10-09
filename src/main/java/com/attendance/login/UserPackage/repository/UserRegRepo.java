package com.attendance.login.UserPackage.repository;

import com.attendance.login.UserPackage.models.UsersReg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegRepo extends JpaRepository<UsersReg,String> {
    UsersReg getByCardnumber(String cardnumber);
}
