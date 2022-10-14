package com.attendance.login.UserPackage.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name="userreg")
@NoArgsConstructor
public class UsersReg {
    @Id
    private String phone;
    private String firstname;
    private String lastname;
    private String cardnumber="0";
    private String category="0";
    private String housname;
    private String wardname;
    private String wardnumber;
    private String postoffice;
    private String district;
    private String pincode;
    private String gender;
    private String dateenrolled= String.valueOf(LocalDate.now());
    private String expirydate="0";
    private String status="T";


//    otp:{
//        type:String,
//        default:null
//    },
//
//    gender:{
//        type:String,
//    },
//    dob:{
//        type:Date,
//    },
//    dateEnrolled:{
//        type:Date,
//        default:Date.now()
//
//    },
//    expiryDate:{
//        type:Date,
//    },
//
//    status: {
//        type: String,
//        default:"T"
//    }


}
