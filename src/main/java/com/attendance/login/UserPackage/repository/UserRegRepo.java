package com.attendance.login.UserPackage.repository;

import com.attendance.login.UserPackage.models.UsersReg;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface UserRegRepo extends JpaRepository<UsersReg,String> {
    UsersReg getByCardnumber(String cardnumber);

    Object getByPhone(String phone);

    void deleteByPhone(String phone);
}
