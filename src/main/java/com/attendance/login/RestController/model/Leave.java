package com.attendance.login.RestController.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name="leave")
public class Leave {



    @Id
    @Email
    public String email;

//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public int id;
}
