package com.attendance.login.Library.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="hold")
public class Hold {
//    accessionNo: String,
//    bookName:String,
//    car
//    dNumber: String,
//    userName: String,
//    houseName: String,
//    wardName: String,
//    wardNumber: String,
//    postOffice: String,
//    pincode: String,
//    phoneNumber: String,
//    checkoutStatus:{
//        type:String,
//        default:"F"
//    },
//    holdStatus:{
//        type:String,
//        default:"T"
//    }
//

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String accessionno;
    public String username;
    public String bookname;
    public String cardnumber;
    public String housename;
    public String wardname;
    public String wardnumber;
    public String postoffice;
    public String pincode;
    public String phonenumber;
    public String checkoutstatus="F";
    public String holdstatus="T";
    public String holdid;
}
