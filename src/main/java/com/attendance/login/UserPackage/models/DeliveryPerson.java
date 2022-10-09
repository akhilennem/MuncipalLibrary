package com.attendance.login.UserPackage.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name="deliveryperson")
public class DeliveryPerson {
    @Id
    private String email;
    private String phone;
    private String name;
    private String ward;
    private String address;
    private int otp;
}
