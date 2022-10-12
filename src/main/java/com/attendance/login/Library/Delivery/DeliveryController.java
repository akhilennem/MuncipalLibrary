package com.attendance.login.Library.Delivery;

import com.attendance.login.Library.Models.Book;
import com.attendance.login.Library.Models.Delivery;
import com.attendance.login.Library.Models.Hold;
import com.attendance.login.Library.Repository.DeliveryRepo;
import com.attendance.login.Library.Repository.HoldRepo;
import com.attendance.login.OtpSender.OtpRepository.OtpRepository;
import com.attendance.login.UserPackage.models.DeliveryPerson;
import com.attendance.login.UserPackage.models.UsersReg;
import com.attendance.login.Library.Repository.BookRepo;
import com.attendance.login.UserPackage.repository.DeliveryPerRepo;
import com.attendance.login.UserPackage.repository.UserRegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    @Autowired
    BookRepo bookRepo;
    @Autowired
    HoldRepo holdRepo;
    @Autowired
    UserRegRepo userRegRepo;
    @Autowired
    DeliveryRepo deliveryRepo;
    @Autowired
    DeliveryPerRepo deliveryPerRepo;
    @Autowired
    OtpRepository otpRepository;


    @PostMapping("delivery-reg")
    public DeliveryPerson addDel(DeliveryPerson deliveryPerson)
    {
        return deliveryPerRepo.save(deliveryPerson);
    }

//    @PostMapping("place-order")
//    public String placeOrder(@RequestParam String accessionno,String cardnumber)
//    {
//            UsersReg usersReg;
//            usersReg= (UsersReg) userRegRepo.getByCardnumber(cardnumber);
//            Book book= (Book) bookRepo.getByAccessionno(accessionno);
//        System.out.println(".......................");
//            System.out.println(book);
//            Hold hold1= new Hold();
//            hold1.accessionno=accessionno;
//            hold1.setCardnumber(cardnumber);
//            hold1.setUsername(usersReg.getFirstname());
//            hold1.setHousename(usersReg.getHousname());
//            hold1.setPincode(usersReg.getPincode());
//            hold1.setPhonenumber(usersReg.getPhone());
//            hold1.setWardname(usersReg.getWardname());
//            hold1.setWardnumber(usersReg.getWardnumber());
//            hold1.setPostoffice(usersReg.getPostoffice());
//            hold1.setBookname(book.booktitle);
//            holdRepo.save(hold1);
//            return "Order Is On Hold";
//        }

    @PostMapping("accept-order")
    public String acceptOrder(@RequestParam String response,String accessionno,String cardnumber) {
        if (response.equals("accepted")) {
            UsersReg usersReg;
            usersReg = (UsersReg) userRegRepo.getByCardnumber(cardnumber);
            Book book = (Book) bookRepo.getByAccessionno(accessionno);
            System.out.println("elements.........");
            System.out.println(usersReg);
            System.out.println(book);
            Delivery delivery = new Delivery();
            System.out.println("elements.........");
            delivery.setAccessionno(accessionno);
            delivery.setCardnumber(cardnumber);
            delivery.setHousename(usersReg.getHousname());
            delivery.setPostoffice(usersReg.getPostoffice());
            delivery.setPincode(usersReg.getPincode());
            delivery.setUserphone(usersReg.getPhone());
            delivery.setWardname(usersReg.getWardname());
            delivery.setWardnumber(usersReg.getWardnumber());
            delivery.setPostoffice(usersReg.getPostoffice());
            delivery.setBookname(book.booktitle);
            delivery.setCheckoutstatus("F");
            delivery.setHoldstatus("F");
            delivery.setCheckinstatus("F");
            delivery.setDpinhand("T");
            delivery.setUserinhand("F");
            delivery.setDistrict(usersReg.getDistrict());

            deliveryRepo.save(delivery);
            return "Waiting for Delivery Partner";
        }
        else {
            return "Order Cancelled";
        }
    }

    @PostMapping("delivery-confirm")
    public Object deliveryConfirm(String phone, String accessionno)
    {
        if (deliveryRepo.existsByAccessionno(accessionno))
        {
            Delivery delivery1;
            DeliveryPerson deliveryPerson1=new DeliveryPerson();
             deliveryPerson1=  deliveryPerRepo.getByPhone(phone);
             delivery1=deliveryRepo.getByAccessionno(accessionno);
//             return delivery1;
        delivery1.setDpphone(deliveryPerson1.getPhone());
        delivery1.setDeliveryperson(deliveryPerson1.getName());
        delivery1.setDpinhand("T");
        deliveryRepo.save(delivery1);
           //  return Collections.singleton(deliveryPerson1);
return "Delivery Boy Accepted the Request";

    }
        else {

            return "failed...........";
        }
    }


    @PostMapping("delivery-complete")
    public Object orderComplete(String phone, String accessionno) {
        if (deliveryRepo.existsByAccessionno(accessionno)) {
            Delivery delivery1;
            DeliveryPerson deliveryPerson1 = new DeliveryPerson();
            deliveryPerson1 = deliveryPerRepo.getByPhone(phone);
            delivery1 = deliveryRepo.getByAccessionno(accessionno);
//             return delivery1;
//            delivery1.setDpphone(deliveryPerson1.getPhone());
//            delivery1.setDeliveryperson(deliveryPerson1.getName());
            delivery1.setDpinhand("F");
            delivery1.setUserinhand("T");
            deliveryRepo.save(delivery1);
            //  return Collections.singleton(deliveryPerson1);
            return "Delivery Completed";

        } else {
            return "failed";
        }
    }

@PostMapping("add-delivery-boy")
    public DeliveryPerson addDeliv(DeliveryPerson deliveryPerson)
{
    return deliveryPerRepo.save(deliveryPerson);
}


    }

