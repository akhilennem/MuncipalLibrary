package com.attendance.login.UserPackage.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Array;
import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
@Table(name="deliveryperson")
public class DeliveryPerson {
    @Id
    private String phone;
    private String name;
    public String[] ward;
    private String address;
    private String verified="0";

}
