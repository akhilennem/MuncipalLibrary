package com.attendance.login.OtpSender.OtpVerifier;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name="otp")
public class OtpVerifier {
//   @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//   public int id;

   @Id
   public String phoneoremail;
   public String otp;
}
