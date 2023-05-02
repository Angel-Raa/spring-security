package com.caja.ideal.user;

import com.caja.ideal.DTO.UserDTO;
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
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDTO>> allUser(){
        return service.allUser();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserModels> user(@PathVariable @Min(1) Long id){
        return service.user(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserModels> postUser(@Valid @RequestBody UserModels user){
        return  service.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserModels> putUser(@Valid @RequestBody UserModels user, @PathVariable @Min(1) Long id){
        return service.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Object> deleteUser(@PathVariable @Min(1) Long id){
        return  service.delete(id);
    }
}
