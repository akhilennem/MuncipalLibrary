package com.attendance.login.Library.Controller;

import com.attendance.login.Library.Models.Book;
import com.attendance.login.Library.Models.Hold;
import com.attendance.login.Library.Repository.BookRepo;
import com.attendance.login.Library.Repository.HoldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    //    @Autowired
//    public Book book;
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

    @GetMapping("/get-by-author")
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

//    @GetMapping("/get-trends")
//    public Iterable<Book> getTrends() {
//        return (Iterable<Book>) bookRepo.getAllByTrends();
//    }
//
//    @GetMapping("/get-by-accessionno")
//    public Iterable<Book> getReleasaes() {
//        return (Iterable<Book>) bookRepo.findByReleases();
//    }

    @GetMapping("/get-all-category")
    public Iterable<Book> getcategory() {
        return (Iterable<Book>) bookRepo.findAll();

    }


    @PostMapping("/order-book")
    public String orderbook(@RequestParam String accessionno, String cardnumber, String housename,
                            String wardname, String wardnumber, String postoffice, String pincode, String phonenumber) {
        Book book = (Book) bookRepo.findByAccessionno(accessionno);
        System.out.printf(".........." + book.category + "\n");
        System.out.println(book.booktitle + "\n");
        System.out.println(book);
        Hold hold1 = new Hold();
        hold1.accessionno = accessionno;
        hold1.setCardnumber(cardnumber);
        hold1.setHousename(housename);
        hold1.setPincode(pincode);
        hold1.setPhonenumber(phonenumber);
        hold1.setWardname(wardname);
        hold1.setWardnumber(wardnumber);
        hold1.setPostoffice(postoffice);
        hold1.setBookname(book.booktitle);
        holdRepo.save(hold1);
        return "null";
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
