package com.attendance.login.Library.Delivery;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Data
@Table(name="deliverypartner")
@NoArgsConstructor
public class DeliveryPartner {
@Nullable
    private String phone;
    @Id
    private String email;
    @Nullable

    private String name;
 @Nullable
    private String address;
    @Nullable
    private String ward;

}
