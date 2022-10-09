package com.attendance.login.Library.Repository;

import com.attendance.login.Library.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookRepo extends JpaRepository<Book,Long> {
    Iterable<Book> getByAuthor(String author);

    Iterable<Book>  getByBooktitle(String name);

    Iterable<Book>  getByLanguage(String language);

    Book getByAccessionno(String accessionno);

    Object findByAccessionno(String accessionno);






//    Iterable<Book> findByReleases();
//
//    Iterable<Book> getAllByTrends();
//
//
//
//    Collection<Object> findAllCategory();
}
