package com.attendance.login.Library.Repository;

import com.attendance.login.Library.Models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface BookRepo extends JpaRepository<Book,Long> {
    Iterable<Book> getByAuthor(String author);

    Iterable<Book>  getByBooktitle(String name);

    Iterable<Book>  getByLanguage(String language);

    Book getByAccessionno(String accessionno);

    Object findByAccessionno(String accessionno);

    @Query("SELECT b FROM Book b WHERE b.booktitle LIKE %?1%")
    List<Book> search(String keyword);



    @Query("SELECT b FROM Book b WHERE b.booktitle LIKE %?1%")
    Page<Book> pagenation(PageRequest of, String keyword);


    Object findByNewreleases();

    Object findByTrends();
}
