package com.caja.ideal.service.impl;

import com.caja.ideal.exception.ResourceNotFound;
import com.caja.ideal.models.Book;
import com.caja.ideal.repository.IBookRepository;
import com.caja.ideal.service.IBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private  IBookRepository repository;
    @Override
    @Transactional(readOnly = true )
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> list = repository.findAll().stream().toList();
        return ResponseEntity.ok(list);
    }

    @Override
    @Transactional(readOnly = true )
    public ResponseEntity<Book> getById(Long id) {
        Book book = repository.findById(id).orElseThrow(()-> new ResourceNotFound("this book does not exist"));
        return ResponseEntity.ok(book);
    }

    @Override
    @Transactional
    public ResponseEntity<Book> save(Book book) {
        Book save = repository.save(book);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Book> update(Long id, Book book) {
        Book bookToBook = repository.findById(id).orElseThrow(()-> new ResourceNotFound("this book does not exist"));
        BeanUtils.copyProperties(book, bookToBook, "id");
        Book update = repository.save(bookToBook);
        return ResponseEntity.ok(update);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        Map<String, String> responde = new HashMap<>();
        Optional<Book> book = repository.findById(id);
        if (book.isPresent()){
            repository.findById(id);
            responde.put("Message", "successfully removed");
            return ResponseEntity.ok(responde);
        }
        responde.put("message", "does not exist");
        return ResponseEntity.badRequest().body(responde);
    }
}
