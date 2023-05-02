package com.caja.ideal.service;

import com.caja.ideal.models.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
   ResponseEntity<List<Book>>getAllBook();
    ResponseEntity<Book> getById(Long id);
    ResponseEntity<Book> save(Book book);
    ResponseEntity<Book> update(Long id, Book book);
    ResponseEntity<Object> delete(Long id);

}
