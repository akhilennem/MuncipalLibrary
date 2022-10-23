package com.attendance.login.Library.Controller;

import com.attendance.login.Library.Models.Book;
import com.attendance.login.Library.Models.Delivery;
import com.attendance.login.Library.Models.Hold;
import com.attendance.login.Library.Repository.BookRepo;
import com.attendance.login.Library.Repository.DeliveryRepo;
import com.attendance.login.Library.Repository.HoldRepo;
import com.attendance.login.UserPackage.models.UsersReg;
import com.attendance.login.UserPackage.repository.UserRegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("api/book")
public class BookController {

        @Autowired
    public UserRegRepo userRegRepo;
    @Autowired
    BookRepo bookRepo;

    Hold hold;
    @Autowired
    HoldRepo holdRepo;
    String line = "";

    @PostMapping("/add-books")
    public Book addbook(Book book) {

        bookRepo.save(book);
        return book;
    }

    @GetMapping("/get-all")
    public Iterable<Book> getAll() {
        return (Iterable<Book>) bookRepo.findAll();
    }

    @GetMapping("/author")
    public Iterable<Book> getByAuthor(String author) {
        return (Iterable<Book>) bookRepo.getByAuthor(author);
    }

    @GetMapping("/get-by-name")
    public Iterable<Book> getByName(String booktitle) {
        return bookRepo.getByBooktitle(booktitle);
    }

    @GetMapping("/get-by-language")
    public Iterable<Book> getByLanguage(String language) {
        return (Iterable<Book>) bookRepo.getByLanguage(language);
    }

    @GetMapping("/get-by-accessionno")
    public Iterable<Book> getByAcceNo(String accessionno) {
        return (Iterable<Book>) bookRepo.getByAccessionno(accessionno);
    }

    @GetMapping("/trends")
    public String getTrends(@RequestParam String accessionno) {
        Book book;
        book=bookRepo.getByAccessionno(accessionno);
        book.trends="T";
        bookRepo.save(book);
return  "Saved";
    }
    @GetMapping("/delete-trends")
    public String deleteTrends(@RequestParam String accessionno) {
        Book book;
        book=bookRepo.getByAccessionno(accessionno);
        book.trends="F";
        bookRepo.save(book);
        return  "Saved";
    }
    @GetMapping("/new-release")
    public String newrelease(@RequestParam String accessionno) {
        Book book;
        book=bookRepo.getByAccessionno(accessionno);
        book.release="T";
        bookRepo.save(book);
        return  "Saved";
    }
    @GetMapping("/delete-release")
    public String deleterelease(@RequestParam String accessionno) {
        Book book;
        book=bookRepo.getByAccessionno(accessionno);
        book.release="F";
        bookRepo.save(book);
        return  "Saved";
    }

//    @GetMapping("/get-by-accessionno")
//    public Iterable<Book> getReleasaes() {
//        return (Iterable<Book>) bookRepo.findByReleases();
//    }

    @GetMapping("/get-all-category")
    public Iterable<Book> getcategory() {
        return (Iterable<Book>) bookRepo.findAll();

    }


    @PostMapping("/request-book")
    public String reqBook(String accessionno,String cardnumber) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        String chars="1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 15; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        System.out.println("random number is:: "+ sb);
        String holdid= String.valueOf(sb);













        UsersReg usersReg=new UsersReg();
        System.out.println("....executed0....");
        Book book = (Book) bookRepo.getByAccessionno(accessionno);
        System.out.println("....executed....1");
        usersReg = userRegRepo.getByCardnumber(cardnumber);
        System.out.println("....executed....2");
        if (cardnumber.equals("0")) {
            return "you are not a verified member";
        } else {

            System.out.println(book);
            Hold hold1 = new Hold();
            hold1.setAccessionno(accessionno);
            hold1.setCardnumber(cardnumber);
            hold1.setHoldid(holdid);
            hold1.setUsername(usersReg.getFirstname());
            hold1.setHousename(usersReg.getHousname());
            hold1.setPincode(usersReg.getPincode());
            hold1.setPhonenumber(usersReg.getPhone());
            hold1.setWardname(usersReg.getWardname());
            hold1.setWardnumber(usersReg.getWardnumber());
            hold1.setPostoffice(usersReg.getPostoffice());
            hold1.setBookname(book.booktitle);
            holdRepo.save(hold1);
            return "Order Is On Hold";
        }
    }
//
//    @PostMapping("save-books")
//    public String savebook() {
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Details.csv"));
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] data = line.split(",");
//                Book book = new Book();
//                book.booktitle = data[0];
//                book.isbn = data[1];
//                book.language = data[2];
//                book.publicationplace = data[3];
//                book.publisher = data[4];
//                book.setPublicationdate(data[5]);
//                book.author = data[6];
//                book.editorortranslator = data[7];
//                book.volume = data[8];
//                book.price = data[9];
//                book.pages = data[10];
//                book.edition = data[11];
//                book.category = data[12];
//                book.classno = data[13];
//                book.accessionno = data[14];
//                book.callno = data[15];
//                book.subjectheading = data[16];
//                book.description = data[17];
//                bookRepo.save(book);
//            }
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//
//        }
//        return null;
//    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String keyword)
    {
        if(keyword!=null)
        {
           return bookRepo.search(keyword);
        }
       return (bookRepo.findAll());
    }

    @PostMapping("/page")
    public Page<Book> pagenation(int off, int on, String keyword)
    {
        if(keyword!=null)
        {
            return bookRepo.pagenation(PageRequest.of(off,on),keyword);
        }
        return (Page<Book>) bookRepo.findAll();
    }
                               //....................................................//
    @PostMapping("membership-requests")
    public List<UsersReg> memberRequests()
    {
        String cardnumber="0";
        return (List<UsersReg>) userRegRepo.getByCardnumber(cardnumber);
    }

    @GetMapping("accept-requests")
    public String addUser(@RequestParam String cardnumber,@RequestParam String expirydate,@RequestParam String category,String phone)
    {
        UsersReg usersReg;
usersReg= (UsersReg) userRegRepo.getByPhone(phone);
usersReg.setCardnumber(cardnumber);
usersReg.setExpirydate(expirydate);
usersReg.setCategory(category);
userRegRepo.save(usersReg);
return "Successfully Verified";
    }

    @GetMapping("delete-requests")
    public ResponseEntity<?> deleteReqs(@RequestParam String phone)
    {

        userRegRepo.deleteByPhone(phone);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("get-users")
    public List<UsersReg>usersRegs()
    {

       return userRegRepo.getUsers();
    }

    @GetMapping("get-trends")
    public List<Book>getTrends()
    {
        String trends="T";
       return bookRepo.getByTrends(trends);
    }

    @GetMapping("get-releases")
    public List<Book>getDelete()
    {
        String release="T";
        return bookRepo.getByRelease(release);
    }
    @GetMapping("requests")
    public List<Hold>reqs()
    {
        return holdRepo.findAll();
    }
@Autowired
    DeliveryRepo deliveryRepo;
    @GetMapping("accepted-requests")
    public List<Delivery>Delreqs()
    {
        return deliveryRepo.findAll();
    }


    @PostMapping("save-books")
    public String savebook() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Details.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Book book = new Book();
                book.booktitle = data[0];
                book.isbn = data[1];
                book.language = data[2];
                book.publicationplace = data[3];
                book.publisher = data[4];
                book.setPublicationdate(data[5]);
                book.author = data[6];
                book.editorortranslator = data[7];
                book.volume = data[8];
                book.price = data[9];
                book.pages = data[10];
                book.edition = data[11];
                book.category = data[12];
                book.classno = data[13];
                book.accessionno = data[14];
                book.callno = data[15];
                book.subjectheading = data[16];
                book.description = data[17];
                bookRepo.save(book);
            }
        } catch (IOException e) {

            e.printStackTrace();


        }
        return null;
    }

}
