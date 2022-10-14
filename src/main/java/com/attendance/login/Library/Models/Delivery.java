package com.attendance.login.Library.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data@Entity
@Table(name="delivery")
@NoArgsConstructor
public class Delivery {

    private String cardnumber;
    private String userphone;
    private String housename;
    private String wardname;
    private String wardnumber;
    private String postoffice;
    private String district;
    private String pincode;
    private String bookname;
    private String accessionno;
//    private String barcode;
    @Id
    private String holdid;
    private String deliveryperson;
    private String dpphone;
    private String checkoutstatus="T";
    private String dpinhand="F";
    private String userinhand="F";
    private String checkinstatus="F";
    public String holdstatus;
    public String username;
    public String barcode="0";


}
