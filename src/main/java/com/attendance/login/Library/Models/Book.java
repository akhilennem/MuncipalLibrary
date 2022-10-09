package com.attendance.login.Library.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "boo")
@NoArgsConstructor
public class Book {



//    public long phone;
    public String booktitle;
    public String isbn;
    public String language;
    public String publicationplace;
    public String publisher;
    public String Publicationdate;
    public String author;
    public String editorortranslator;
    public String volume;
    public String price;
    public String pages;
    public String edition;
    public String classno;
    @Id
    public String accessionno;
    public String callno;
    public String subjectheading;
    public String description;
    public String category;
    public String trends;
    public String release;
    public String hold;
    public String checkout;
}
