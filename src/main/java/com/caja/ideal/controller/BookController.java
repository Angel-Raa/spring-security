package com.caja.ideal.controller;

import com.caja.ideal.models.Book;
import com.caja.ideal.service.IBookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Validated
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    ResponseEntity<List<Book>> listBook() {
        return service.getAllBook();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    ResponseEntity<Book> book(@PathVariable @Min(1)  Long id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    ResponseEntity<Book> postBook(@Valid @RequestBody Book book) {
        return service.save(book);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    ResponseEntity<Book> putBook(@Valid @RequestBody Book book, @PathVariable @Min(1)  Long id ){
        return service.update(id, book);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> deleteBook(@PathVariable @Min(1) Long id){
        return  service.delete(id);
    }

}
